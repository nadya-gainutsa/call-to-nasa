
**Part I. Build the largest NASA picture web service**

1. Create a new Spring Boot app
2. Build an endpoint GET /pictures/{sol}/largest that redirects the client to the largest picture
  * it should accept 'sol' as a path variable
  * given 'sol' should be used to construct a query and call NASA
     - This is a base "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos"
     - It accepts 'api_key' and 'sol' as query params 
          <br>(e.g. a complete URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=15&api_key=DEMO_KEY")
     - This time use RestTemplate and implement redirect logic manually :exclamation:️
3. Make sure it works and finds max picture per sol (try sol=14 and sol=15)
4. Test it by opening "http://localhost:8080/pictures/14/largest" in browser (you should see a picture)

**Part II. Upgrade your largest NASA picture web service**
1. Configure caching of picture URLs
 * Use a corresponding Spring Boot starter 
2. Configure a scheduled job that clears the cache every day
3. Add actuator and expose all endpoints
