####How to Run
```buildoutcfg
mvn spring-boot:run
```

####How to Build Jar
```buildoutcfg
mvn clean package
```

###APIs
```dtd
POST /v1/fillData
Populates data using the input of format

        POST http://localhost:8080/v1/fillData
        Content-Type: application/json

        {
        "details": [
        {"state":"Maharashtra", "district":"Nagpur", "mandi_name":"ABC", "crop_name":"Tomato", "variety" :"Country", "date":"2021-05-02", "price" :450.0},

        {"state":"Maharashtra", "district":"Nagpur", "mandi_name":"ABC", "crop_name":"Tomato", "variety" :"Hybrid", "date":"2021-05-01", "price" :300.0},
        {"state":"Maharashtra", "district":"Nagpur", "mandi_name":"ABC", "crop_name":"Tomato", "variety" :"Country", "date":"2021-05-02", "price" :200.0},

        {"state":"Maharashtra", "district":"Nagpur", "mandi_name":"XYZ", "crop_name":"Tomato", "variety" :"Hybrid", "date":"2021-05-03", "price" :450.0},
        {"state":"Maharashtra", "district":"Nagpur", "mandi_name":"XYZ", "crop_name":"Tomato", "variety" :"Country", "date":"2021-05-04", "price" :500.0}
        ]

        }

GET /v1/getLatestData
Given state, district and crop, lists down all mandis in given district, for all crop varieties, with latest price & date (Sorted by latest date)

        GET http://localhost:8080/v1/getLatestData?state=Maharashtra&crop=Tomato&district=Nagpur&variety=Hybrid

GET /v1/getDataByLimit
Given mandi, crop variety, shows price trends of x points in last y days

        GET http://localhost:8080/v1/getDataByLimit?mandi=ABC&crop=Tomato&variety=Country&elements=5&limit=6


```