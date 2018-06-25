# Auth service

<img width="880" alt="Auth service" src="https://cloud.githubusercontent.com/assets/6069066/13900465/730f2922-ee20-11e5-8df0-e7b51c668847.png">

#### User Account and Authentication (UAA) 


Method        | Path                      | Description                                                     | User authenticated	| Available from UI
------------- | ------------------------- | ----------------------------------------------------------------|---------------------|------------------|
GET           | /uaa/users/current        | Get specified account data                                      |                     |                  |
POST          | /uaa/users                | Create new user                                                 | ×                   | ×                |
POST          | /uaa/oauth/token          | TokenEndpoint token requests as described in the OAuth2         |                     | ×                |
PUT           | /uaa/oauth/authorize      | AuthorizationEndpoint Accepts authorization requests            | ×                   | ×                |
POST          | /accounts/                | Register new account                                            |                     | ×                |

#### Authorization Service
#### Resource Service