# SyncProject
Project for Sync problem statement

A Simple Product storage app which user MySQL as database and Redis caching for optimized data reterival.

1.  For Installation Setup please follow "Setup_Installation Steps.docx"
2.  For End point testing please follow "API testing End points.docx"
3.  For Error handling please follow "API testing Errro Handling.docx"
4.  For Redis Cache and parallel processing follow "redis caching and parallel processing.docx"

End points:

S. No. | Method Type |          End point          |  Description
1      | GET         | /api/products/welcome       |  End Point to verify whether API is running or not. (Returns a simple welcome string)
2      | GET         | /api/products/getall        |  End point will return All product records from Database
3      | GET         | /api/products/get/{id}      |  End point will return a specific product record
4      | POST        | /api/products/create        |  Used to create new Product record on Database (Body of type product required)
5      | PUT         | /api/products/update/{id}   |  Used to update exisitng record based on 'id' provided (Body of type product required)
6      | DELETE      | /api/products/delete/{id}   |  End point will delete the record from Database based on 'id' provided
7      | GET         | /api/products/parallel      |  End point will execute the parallel processing using ExectorService API
8      | GET         | /api/products/ssldetails    |  Used to get SSL details

NOTE: #8 initially SSL details were disabled due to security reasons. To enable it please uncomment line 133 from com.service.ProductService class.
