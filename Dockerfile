FROM eclipse-temurin:21

WORKDIR /app

COPY . .

RUN javac *.java

EXPOSE 1099

CMD sh -c "rmiregistry 1099 & sleep 5 && java Server"