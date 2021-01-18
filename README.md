# SpringBoot_CampusStore
Transfer framework from SSM to SpringBoot
Tools used:
  1. Spring Boot 2. MySQL 3. MyBatis 4. Redis + Jedis 5. SUI front-end library + Java Script 6. Thumbnailator 7. Kapatcha

Spring Boot:

    1. Interceptors - UserLoginInterceptor (Check if user has logged in, if not redirect to login page)
                      ShopLoginIntercepetor (prevent user who is not a shop owner from viewing the shop system)
                      ShopPermissionInterceptor (prevent shop owner from modifying others' shop)
                      SuperAdminLoginInterceptor(prvent normal user login into admin system)
                      
    2. Controllers - Login System: Supporting login check request, account register request, change account pwd request, log out request
                     User System: page redirect, return shop list, return shop category, return headline info, return product detail
                     Shop System: page redirect, return shop list, return shop info, return product info, supporting category modification, product modification
                     Super AdminSystem: page redirect, shop modification, headline modification, area modification, category modification, account modification
                     
    3. Service: Area Service, HeadLineService, LocalAuthService, PersonInfoService, ProductCategoryService, productService, ShopCategoryService, ShopService
    
    4. Dao: AreaDao, HeadLineDao, LocalAuthDao, PersonInfoDao, ProductCategoryDao, ProductDao, ProductImgDao, ShopCategoryDao, ShopDao
    
    5. Dto: AreaExecution, HeadLineExecution, ImageHolder, LocalAuthExecution, PersonInfoExecution, ProductCategoryExecution, ProductExecution, Result, 
                           ShopCategoryExecution, ShopExecution. Dto Layer is used to package result returned by service layers
    6. util: (1). CodeUtil - Check if verification code generated by Kapatcha is equal to code inputted by users
             (2). HttpServletRequestUtil - transfer the data type got from request by key to data type required
             (3). ImageUtil - Get the Input Stream of image, attaching the watermark on the page and storing it in the disk
             (4). PageCalculator - unify the page row and page size send from front end with MySQL page select.
             (5). PathUtil - Get the target store path of img and generate the random file name to avoid dupilcation problem
    7. MySQL: Hold tables - tb_area, tb_head_line, tb_local_auth, tb_person_info, tb_product, tb_product_category, tb_product_img, tb_shop, tb_shop_category
    8. Thumbnaillator: Read img input stream and attach watermark and store img file into local disk
    9. Kapatcha: Register kapatcha servlet for generating verification code and send to front-end
    10. Redis + Jedis: Used as cache for lazy data like area, shop category, headline.
    
                     




Project Structure:

![image](https://github.com/GarrisonGE/SpringBoot_CampusStore/blob/master/img.png)