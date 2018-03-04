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

