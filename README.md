# Login Appilcation

A simple springboot application that you can use to upload images and view them. It makes use of JWT authentication for securing the REST APIs.


# Stack

 1. Springoot
 2. s3
 3. postgres
 4. Angular


## JWT
Used JSON Web Tokens for authentication. 
Reference : [https://www.javainuse.com/spring/boot-jwt](https://www.javainuse.com/spring/boot-jwt)

## Database 

Used Postgres as DB. The Database design is depicted below
| user |  |
|--|--|
| id |[pk]  |
| username |varchar  |
| password |password  |

| user_images |  |
|--|--|
| id |[pk]  |
| url |varchar  |
| user_id |[fk]  |


## API Documentation

 - 	**POST /authenticate**
			*Request*
  
    	{
		    "username":"helloworld",
		    "password":"password"
	    }
		
	*Response*
		
        {
		    "userId": 2,
		    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZW....
		}


 -  **POST/api/v1/upload/file**
			Uploads to S3		

    	{
	    	file : multipart
	    }

		

 

 - **POST api/v1/write**
	*Request*
	
	    {
			"userId" : 2,
			"imageUrl":"https://images-meta-01.s3.amazonaws.com/1581351584029-file-nature.jpg"
		}
	
	**GET /api/v1/userid/{userid}**
	Get all Images of a userid



