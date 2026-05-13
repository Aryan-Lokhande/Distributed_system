#include <bits/stdc++.h>
using namespace std;
int main(){
    int token = 0;
    int n;
    cout << "Enter the No. of processs: ";
    cin >> n;
    cout << "Ring: ";
    for (int i = 0; i < n; i++){
        cout << " P" << i << " ->";
    }
    cout << " P0" << endl;

    int s, r, data;

    while (true){
        cout << "\n  Token is at P" << token << endl;
        cout << "  Enter the sender process (0-" << (n - 1) << ") ";
        cin >> s;
        cout << "  Enter the Receiver process (0-" << (n - 1) << ") ";
        cin >> r;
        cout << "  Enter the data to send: ";
        cin >> data;
        cout << "\n  PHASE 1:[TOKEN Traversal]\n ";
        // if()
        while (token != s){
            cout << " P" << token << " ->";
            token = (token + 1) % n;
        }
        cout << " P" << token << " (sender receive token)" << endl;

        cout << "\n  PHASE 2:[DATA FORWARDING]\n";
        cout << "  sender forward data to\n  Node:";
        while (token != r){
            cout << " P" << token << " ->";
            token = (token + 1) % n;
        }
        cout << " P" << token << endl;
        cout << "\n  P" << token << " (reciever receive token and data) data: " << data << endl;
        cout << "Token is empty again!!!" << endl;
    }

    return 0;
}