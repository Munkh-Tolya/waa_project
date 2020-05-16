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
        /* USER START */
        Admin admin = new Admin("admin","admin","admin@miu.edu","$2a$10$/8XXI4KpM2cp8evP7.NXjOmLLXFNHj9VtqB9wOUojWOk3fAISRvx.");

        Seller seller = new Seller("Uugan","Bayar","uugnaa@miu.edu","$2a$10$gKkVcQ71UXf7yC3l1A4cD.C5YAD5dYo6cUQyyS4J/Q5qokZnJ94x.");
        Seller seller1 = new Seller("Otgon","Bayar","oogii@miu.edu","$2a$10$gKkVcQ71UXf7yC3l1A4cD.C5YAD5dYo6cUQyyS4J/Q5qokZnJ94x.");

        Buyer buyer = new Buyer("Od","Bayar","odko@miu.edu","$2a$10$rH.SaKTlzH0W4mbQ6JkZz.Ss7whuKuwBUyFIkr1OY.15SYSC0jJ2O");
        Buyer buyer1 = new Buyer("Munkh","Erdene","munkh@miu.edu","$2a$10$rH.SaKTlzH0W4mbQ6JkZz.Ss7whuKuwBUyFIkr1OY.15SYSC0jJ2O");

        buyer.addFollowing(seller);
        buyer1.addFollowing(seller);

        userRepository.saveAll(Arrays.asList(admin,seller,seller1,buyer,buyer1));
        /* USER END */

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
        Product p3 = new Product("Ultra Whitte Wireless",c1Desc,350,"uploads/pro3.png",10,c1,seller);
        Product p4 = new Product("Wireless Mondo",c1Desc,266,"uploads/pro33.png",10,c1,seller);

        Product p5 = new Product("Tablet Red 8500U 2TB",c2Desc,100,"uploads/pro4.png",10,c2,seller);
        Product p6 = new Product("Laptop Sens 7200L",c2Desc,760,"uploads/pro8.png",10,c2,seller);

        Product p8 = new Product("Ellite Pro Book 15'6 7500U", c2Desc,850, "uploads/pro5.png", 10,c2, seller);
        Product p9 = new Product("Tablet VX3000 Extra Light", c2Desc,850, "uploads/pro6.png", 10,c2, seller);
        Product p10 = new Product("Tablet VX4000 8500 3TB", c2Desc,940, "uploads/pro7.png", 10,c2, seller);
        Product p11 = new Product("Laptop XS3000 WiFi Smart", c2Desc,1200, "uploads/pro9.png", 10,c2, seller);

        Product p12 = new Product("Smartphone Tablet Spring2000", c3Desc,400, "uploads/pro10.png", 10,c3, seller1);
        Product p13 = new Product("Extra Thin Elitte", c3Desc,700, "uploads/pro11.png", 10,c3, seller1);
        Product p14 = new Product("Notebook Polo 4000", c3Desc,399, "uploads/pro11.png", 10,c3, seller1);
        Product p15 = new Product("Smartphone Elitte Pro", c3Desc,699, "uploads/pro13.png", 10,c3, seller1);
        Product p16 = new Product("Smartphone XD5000", c3Desc,799, "uploads/pro14.png", 10,c3, seller1);

        Product p17 = new Product("Camera Xd Pro With Waterproof Cover", c4Desc,499, "uploads/pro15.png", 10,c4, seller1);
        Product p18 = new Product("Camera HD200 X100", c4Desc,479, "uploads/pro16.png", 10,c4, seller1);
        Product p19 = new Product("Smart Camera Extra Mini2000", c4Desc,799, "uploads/pro17.png", 10,c4, seller1);
        Product p20 = new Product("ONTEC Camera W5000", c4Desc,400, "uploads/pro18.png", 10,c4, seller1);
        Product p21 = new Product("Classic Camera E5000", c4Desc,400, "uploads/pro19.png", 10,c4, seller1);

        Product p22 = new Product("TV Premium 2000", c5Desc,799, "uploads/pro20.png", 10,c5, seller1);
        Product p23 = new Product("Camera HD200 X100", c5Desc,799, "uploads/pro21.png", 10,c5, seller1);
        Product p24 = new Product("Smart Camera Extra Mini2000", c5Desc,900, "uploads/pro22.png", 10,c5, seller1);
        Product p25 = new Product("ONTEC Camera W5000", c5Desc,399, "uploads/pro23.png", 10,c5, seller1);

        Product p26 = new Product("Smart Watch", c6Desc,366, "uploads/pro24.png", 10,c6, seller1);
        Product p27 = new Product("Smart Watch Zoop", c6Desc,100, "uploads/pro25.png", 10,c6, seller1);
        Product p28 = new Product("Hybrid Smartwatch Waterproof", c6Desc,100, "uploads/pro26.png", 10,c6, seller1);

        /* PRODUCT END */

        /* PRODUCT REVIEW START */
        p1.addReview(new ProductReview(5,"This product is very good!",buyer,true));
        p1.addReview(new ProductReview(4,"This product is good but not so comfortable!",buyer1,true));
        p2.addReview(new ProductReview(5,"I bought it months ago, It works well!",buyer,true));
        p2.addReview(new ProductReview(2,"It did not come in time!!",buyer1,true));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28));
        /* PRODUCT REVIEW END */

        /* ITEM START */
        Item i1 = new Item(p1,4);
        Item i2 = new Item(p2,3);
        Item i3 = new Item(p3,4);
        Item i4 = new Item(p4,3);
        Item i20= new Item(p20,5);
        Item i21 = new Item(p21,3);
        Item i22= new Item(p22,5);
        Item i23 = new Item(p23,3);
        itemRepository.saveAll(Arrays.asList(i1,i2,i3,i4,i20,i21,i22,i23));
        /* ITEM END */

        /* ORDER START */
        Order o1 = new Order(new Date(), Arrays.asList(i1,i2), buyer, seller);
        Order o2 = new Order(new Date(), Arrays.asList(i3,i4), buyer, seller);
        Address address = new Address("1000N 4th str","Fairfield","IA","United states","52557");
        Payment payment = new Payment("5257138872690125", "JOHN DOE",4,2024);
        Address address1 = new Address("1000N 4th str","Chicago","IL","United states","52557");
        Payment payment1 = new Payment("5257138872690125", "STEPHEN CURRY",3,2024);
        o1.setShippingAddress(address);
        o1.setBillingAddress(address);
        o1.setPayment(payment);
        o2.setShippingAddress(address1);
        o2.setBillingAddress(address1);
        o2.setPayment(payment1);


        Order o3 = new Order(new Date(), Arrays.asList(i20,i21), buyer1, seller1);
        Order o4 = new Order(new Date(), Arrays.asList(i22,i23), buyer1, seller1);
        Address address3 = new Address("1000N 4th str","Fairfield","IA","United states","52557");
        Payment payment3 = new Payment("1111222233334444", "JOHN DOE",4,2024);
        Address address4 = new Address("1000N 4th str","Chicago","IL","United states","52557");
        Payment payment4 = new Payment("1111222233334444", "STEPHEN CURRY",3,2024);
        o3.setShippingAddress(address3);
        o3.setBillingAddress(address3);
        o3.setPayment(payment3);
        o4.setShippingAddress(address4);
        o4.setBillingAddress(address4);
        o4.setPayment(payment4);

        orderRepository.saveAll(Arrays.asList(o1,o2,o3,o4));
        /* ORDER END */

        userRepository.saveAll(Arrays.asList(admin,seller,seller1,buyer,buyer1));
    }
}
