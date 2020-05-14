package edu.miu.cs545.waa_project;

import edu.miu.cs545.waa_project.domain.*;
import edu.miu.cs545.waa_project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class WaaProjectApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(WaaProjectApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception
    {
        Admin admin = new Admin("admin","admin","admin@miu.edu","$2a$10$/8XXI4KpM2cp8evP7.NXjOmLLXFNHj9VtqB9wOUojWOk3fAISRvx.");
        Seller seller = new Seller("seller","seller","seller@miu.edu","$2a$10$gKkVcQ71UXf7yC3l1A4cD.C5YAD5dYo6cUQyyS4J/Q5qokZnJ94x.");
        Seller seller1 = new Seller("seller1","seller1","seller1@miu.edu","$2a$10$gKkVcQ71UXf7yC3l1A4cD.C5YAD5dYo6cUQyyS4J/Q5qokZnJ94x.");
        Buyer buyer = new Buyer("buyer","buyer","buyer@miu.edu","$2a$10$rH.SaKTlzH0W4mbQ6JkZz.Ss7whuKuwBUyFIkr1OY.15SYSC0jJ2O");
        userRepository.saveAll(Arrays.asList(admin,seller,seller1,buyer));

        /* CATEGORY START */
        Category c1 = new Category("Headphones");
        Category c2 = new Category("Laptops");
        Category c3 = new Category("Smartphones");
        Category c4 = new Category("Cameras");
        Category c5 = new Category("TV & Audio");
        Category c6 = new Category("Watches");
        categoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
        /* CATEGORY END */

        /* Description for products by categegory added by Otgonbayar*/
        String c1Desc = "Superior sound enjoy clear sound and supreme comfort with the OneOdio Studio monitor headphones Large 50 millimeter speaker unit drivers combined with neodymium magnets powerful bass clear vocal and crisp high tones form perfect hi-fi sound";
        String c2Desc = "10th Gen Intel Core i5-1035G4 Processor at 1.1GHz, up to 3.7GHz Max Turbo Frequency with 6MB Intel Smart Cache. Give you the power to surf, stream, and do so much more. Edit photos and videos faster than ever, and move between programs and Windows quickly.";
        String c3Desc = "OS: OxygenOS based on Android 10, Processor: Qualcomm Snapdragon 855 Plus (Octa-core, 7nm, up to 2.96 GHz) , with Qualcomm AI Engine , Rear camera - Main Sensor: Sony IMX586 Megapixels: 48, Telephoto Lens Megapixels: 12, Ultra Wide Angle Lens Megapixels: 16, Front Camera: Megapixels: 16, Display: Size: 6.55 inch Resolution: 2400 x 1080 Pixels 402 PPI Aspect Ratio: 20:9 Type: AMOLED. ";
        String c4Desc = "Kit Includes: Canon PowerShot SX420 IS Digital Camera + NB-11LH Lithium-Ion Battery Pack + CB-2LF Battery Charger + Lens Cap + WS-DC12 Wrist Strap + Limited 1-Year Warranty (All these in Canon box). + 64GB High-Speed SDXC Class 10 Memory Card + Card Reader + Memory Card Wallet + Screen Protector + Vidpro Digital Camera Cleaning Kit";
        String c5Desc = " Fire TV experience built-in - Fire TV Edition brings together live-over-the air TV, and your favorite streaming content on the home screen. Connect any HD antenna (sold separately) to watch live over-the-air TV, or stream movies and shows from Disney+, Netflix, Prime Video, Hulu, HBO and more. Dolby Vision HDR - Enhanced 4K HDR picture with an expanded range of contrast and superior brightness";
        String c6Desc = "If you live an active lifestyle and want to refine your training or you are a newbie and want to get active,or if you want an accurate waterproof tracker that adds a ton of smart watch features and has great battery (7-10 days),yamay 020 is right one to help you stay motivated and stay healthy";
        /* end of Description*/

        /* PRODUCT START */
        Product p1 = new Product("ONTEC E Headset",c1Desc,175,"uploads/pro1.png",10,c1,seller);
        Product p2 = new Product("Solo Headset",c1Desc,235,"uploads/pro2.png",10,c1,seller);
        Product p3 = new Product("Ultra Whitte Wireless",c1Desc,350,"uploads/pro3.png",10,c1,seller1);
        Product p4 = new Product("Wireless Mondo",c1Desc,266,"uploads/pro33.png",10,c1,seller1);

        Product p5 = new Product("Tablet Red 8500U 2TB",c2Desc,100,"uploads/pro4.png",10,c2,seller);
        Product p6 = new Product("Laptop Sens 7200L",c2Desc,760,"uploads/pro8.png",10,c2,seller);
        /* PRODUCT END */

        /* PRODUCT REVIEW START */
        p1.addReview(new ProductReview(5,"This product is very good!",buyer,true));
        p2.addReview(new ProductReview(4,"This product is very good!",buyer,true));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
        /* PRODUCT REVIEW END */

        /* ITEM START */
        Item i1 = new Item(p1,4);
        Item i2 = new Item(p2,3);
        Item i3= new Item(p3,5);
        Item i4 = new Item(p5,3);
        Item i5= new Item(p6,5);
        itemRepository.saveAll(Arrays.asList(i1,i2,i3,i4,i5));
        buyer.addCardItem(i1);
        buyer.addCardItem(i2);
        buyer.addCardItem(i3);
        /* ITEM END */

        /* ORDER START */
        Order o1 = new Order(new Date(), Arrays.asList(i1,i2));
        Order o2 = new Order(new Date(), Arrays.asList(i4,i5));
        orderRepository.save(o1);
        orderRepository.save(o2);
        buyer.addOrder(o1);
        buyer.addOrder(o2);
        seller.addOrder(o1);
        seller.addOrder(o2);

        /* ORDER END */

        /* USER FOLLOWING START */
        buyer.addFollowing(seller);
        /* USER FOLLOWING END */

        userRepository.saveAll(Arrays.asList(admin,seller,seller1,buyer));
    }
}
