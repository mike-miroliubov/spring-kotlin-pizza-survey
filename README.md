# A Spring Boot + Kotlin Showcase: Pizza topping survey app 
## Running locally

### Requirements
* Java 17
* Docker

### Steps to build and run
1. Run database in Docker
```
cd deployment
docker compose up -d
```

2. Build the application
```
cd ..
./gradlew build
```

3. Run
```
java -jar build/libs/topping-survey-0.0.1-SNAPSHOT.jar
```

## Example queries
### Submit survey
```
curl --location 'http://localhost:8080/v1/survey' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user1@gmail.com",
    "toppings": ["ham", "cheese"]
}'
```

Expected result: 202 Accepted

### Fetch all surveys
```
curl --location 'http://localhost:8080/v1/survey'
```

Expected result:
```
[
    {
        "email": "user2@gmail.com",
        "toppings": [
            "chocolate",
            "cheese"
        ]
    },
    {
        "email": "user3@gmail.com",
        "toppings": [
            "ham",
            "cheese"
        ]
    },
    {
        "email": "user1@gmail.com",
        "toppings": [
            "ham",
            "cheese"
        ]
    }
]
```

### Get survey results
```
curl --location 'http://localhost:8080/v1/survey/results'
```

Expected result:
```
[
    {
        "name": "cheese",
        "count": 3
    },
    {
        "name": "ham",
        "count": 2
    },
    {
        "name": "chocolate",
        "count": 1
    }
]
```

## My personal preferences :)
```
curl --location 'http://localhost:8080/v1/survey/authors_choice'
```

Expected result:
```
{
    "email": "mike.miroliubov@gmail.com",
    "toppings": [
        "ham",
        "cheese",
        "pineapple"
    ]
}
```