Application is built with spring boot using in memory database H2. 

## Setup

**Step 1:**  Clone repository

```sh
 git clone https://github.com/kirvinod/mybbcPush.git
```

**Step 2:** Go to root directory

```sh
cd mybbcPush/
```

**Step 3:** Run application

```sh
mvn spring-boot:run
```

**Step 4:** Application by default will be serving on port 8080

```sh
http://localhost:8080
```

## Resources List


| Resource           | Method  | Required Params      | Description                                      |
| ------------------ | --------| -----------------    | ------------------------------------------------ |
| /api/user/register | POST    | username,accessToken | Create new user                                  |
| /api/user/list     | GET     | -                    | List all registred users                         |
| /api/user/notify   | POST    | username,type,body   | Send notification(note/file/link) using username |


#### Register user `POST /api/user/register` (valid request)

```sh
curl -i -X POST -H "Content-Type:application/json" -d '{"username": "bbcUser1", "accessToken": "anAccessToken" }'  http://localhost:8080/api/user/register 
```

#### Output

```sh
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:28:06 GMT


{
"username":"bbcuser1",
"accessToken":"anAccessToken",
"numOfNotificationsPushed":0,
"creationTime":"2018-03-04T13:28:06.172"
}
```

#### Register user `POST /api/user/register` (duplicate user)

```sh
curl -i -X POST -H " '{"username": "bbcUser1", "accessToken": "anAccessToken" }'  http://localhost:8080/api/user/register 
```

#### Output
```sh
HTTP/1.1 409 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:31:48 GMT

{
"status":"CONFLICT",
"message":"User with this username already exists.",
"errors":[],
"timestamp":"2018-03-04T13:31:48.330Z"
}
```
#### Register user `POST /api/user/register` (missinig params)

```sh
curl -i -X PO '{"username": "bbcUser1" }'  http://localhost:8080/api/user/register 
```
#### Output
```sh
HTTP/1.1 406 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:35:05 GMT

{
"status":"NOT_ACCEPTABLE",
"message":"Invalid or Missing Parameter(s).",
"errors":["accessToken: Parameter 'accessToken' is required."],
"timestamp":"2018-03-04T13:35:05.386Z"
}
```

