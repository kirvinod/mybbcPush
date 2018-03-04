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

#### List users `GET /api/user/list` 

```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/api/user/list 
```

#### Output
```sh
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:38:50 GMT

[
 {
  "username": "bbcuser1",
  "accessToken": "anAccessToken",
  "numOfNotificationsPushed": 0,
  "creationTime": "2018-03-04T13:28:06.172"
 },
 {
  "username": "bbcuser2",
  "accessToken": "anAccessToken",
  "numOfNotificationsPushed": 0,
  "creationTime": "2018-03-04T13:40:24.292"
 }
]
```


### Notification

Application can send 3 types of notifications: Note, File and Link.

| Resource           | Type    | Required Params      |
| ------------------ | --------| -----------------    |
| /api/user/notify   | note    | username,type,title,body | 
| /api/user/notify   | link    | username,type,title,body,url |
| /api/user/notify   | file    | username,type,body,file_name,file_type,file_url |

#### Send Notification to user `POST /api/user/notify` (valid request)
```sh
curl -i -X POST -H "Content-Type:application/json" -d '{"username": "bbcUser1", "type": "note", "title": "t1", "body":"b1"}'  http://localhost:8080/api/user/notify
```

#### Output
```sh
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:53:25 GMT

{
"status":"OK",
"message":"Notification Sent Successfully!"
}

```

#### Send Notification to user `POST /api/user/notify` (missing params)
```sh
curl -i -X POST -H "Content-Type:application/json" -d '{"username": "bbcUser1", "type": "file",  "body":"b1"}'  http://localhost:8080/api/user/notify
```
#### Output
```sh
HTTP/1.1 406 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 04 Mar 2018 13:55:49 GMT

{
"status":"NOT_ACCEPTABLE",
"message":"Invalid or Missing Parameter(s).",
"errors":[
          "file_type: Parameter 'file_type' is required.",
          "file_name: Parameter 'file_name' is required.",
          "file_url: Parameter 'file_url' is required."
        ],
"timestamp":"2018-03-04T13:55:49.616Z"}
```
