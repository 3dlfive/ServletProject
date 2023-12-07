# ServletProject

In these project will be web app clone of tinder app.Each users have it owen list of likes and chats.

After deploy project have test users. **List of test users:**
<img width="138" alt="image" src="https://github.com/3dlfive/ServletProject/assets/29129086/af83efce-a3a4-4fa6-acb6-9480e5f7a317">

Flyway migration for test data:
```
maven flyway:migrate
```

Project will run with dokcer compose for new environment.

Api lists:
```
/users
/liked
/messages/{id}
/login
```

## DB schema
<img width="373" alt="image" src="https://github.com/3dlfive/ServletProject/assets/29129086/742baca7-57eb-4434-a897-a1dba6f7b56d">


