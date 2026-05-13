#include<iostream>
using namespace std;

int alive[5], coordinator = 5;

void BullyAlgo(int p){
    cout<<"\n [ELECTION] Process P"<<(p)<<" initiates election\n";
    bool newLeader = false;
    for(int i = p+1; i <=5; i++){
        cout<<"   Election msg P"<<p<<" -> P"<<i<<endl;
        if(alive[i-1]){
            cout<<"   P"<<i<<" -> P"<<p<<" msg OK (P"<<p<<" steps back)\n";
            newLeader = true;
            BullyAlgo(i);
            return;
        }else{
            cout<<"   --Process P"<<i<<" is DOWN\n";
        }
    }

    if(!newLeader){
        coordinator = p;
        cout<<"\n No higher Processor is available";
        cout<<"\n [COORDINATOR] P"<<p<<" is now Coordinator\n Announcing to lower processes:";
        for(int i=0; i<coordinator; i++){
            if(alive[i]){
                cout<<"\n  COORDINATOR msg: P"<<coordinator<<" -> P"<<(i+1);
            }
        }
        cout<<endl;
    }
}

void sendMSG(int p){
    if (p < 1 || p > 5) { cout << "Invalid! Enter 1-5.\n"; return; }
    if(!alive[p-1]){
        cout<<"\n P"<<p<<" is DOWN can't send the msg\n";
        return;
    }
    if(alive[coordinator-1]){
        cout<<"\n MSG send to Coordinator\n";
        return;
    }
    cout<<"\n Coordinator is DOWN =[Triggering Election]=\n";
    BullyAlgo(p);
}

void UpProcess(int p){
    if (p < 1 || p > 5) { cout << "Invalid! Enter 1-5.\n"; return; }
    if(alive[p-1]){
        cout<<"The process P"<<p<<" is already UP\n";
        return;
    }
    alive[p-1] = 1;
    cout<<"The process P"<<p<<" is now UP \n";
    BullyAlgo(p);
}
void DownProcess(int p){
    if (p < 1 || p > 5) { cout << "Invalid! Enter 1-5.\n"; return; }
    if(!alive[p-1]){
        cout<<"The process P"<<p<<" is already DOWN\n";
        return;
    }
    alive[p-1] = 0;
    cout<<"The process P"<<p<<" is now DOWN. \n";
}

void status(){
    cout<<"Process:";
    for(int i =0; i < 5; i++){
        cout<<" P"<<(i+1)<<(alive[i] ? "=Up" : "=Down");
    }
    cout<<"\nCoordinator == P"<<coordinator<<endl;
};

int main(){
    for(int i=0; i<5; i++){
        alive[i] = 1;
    }
    status();

    while(true){
        cout<<"\n1. To UP the process\n";
        cout<<"2. To Down the process\n";
        cout<<"3. Send msg (or trigger election if coordinator down)\n";
        cout<<"4. Show Status\n";
        cout<<"5. Exit \n";
        int choice;
        cout<<"Choice: ";
        cin>>choice;
        switch(choice){
            int p;
            case 1:
                cout<<"Which process to Up (1-5): ";
                cin>>p;
                UpProcess(p);
                break;
            case 2:
                cout<<"Which process to DOWN (1-5): ";
                cin>>p;
                DownProcess(p);
                break;
            case 3:
                cout<<"Which process sends message (1-5): ";
                cin>>p;
                sendMSG(p);
                break;
            case 4:
                status();
                break;
            case 5:
                cout<<"Exiting.";
                return 0;

            default:
                cout<<"Invalid choice";
        }
    }
    return 0;
}