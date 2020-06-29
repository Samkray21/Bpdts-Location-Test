package com.bpdts.location;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;

public class DistanceServiceImplTest {

    @Test
    public void testGetAllUsers() {
        DistanceServiceImpl distanceServiceImpl = new DistanceServiceImpl();

        Map<Integer, User> myMap = new HashMap<>();

        User user1 = new User(322, "Hugo", "Lynd", "hlynd8x@merriam-webster.com", "109.0.153.166", 51.6710832d, 0.8078532d);
        User user2 = new User(688, "Tiffi", "Colbertson", "tcolbertsonj3@vimeo.com", "141.49.93.0", 37.13d, -84.08d);
        User user3 = new User(658, "Stephen", "Mapstone", "smapstonei9@bandcamp.com", "187.79.141.124", -8.1844859d, 113.6680747d);
        User user4 = new User(135, "Mechelle", "Boam", "mboam3q@thetimes.co.uk", "113.71.242.187", -6.5115909d, 105.652983d);
        User user5 = new User(520, "Andrew", "Seabrocke", "aseabrockeef@indiegogo.com", "28.146.197.176", 27.69417d, 109.73583d);
        User user6 = new User(554, "Phyllys", "Hebbs", "phebbsfd@umn.edu", "100.89.186.13", 51.5489435d, 0.3860497d);
        User user7 = new User(794, "Katee", "Gopsall", "kgopsallm1@cam.ac.uk", "203.138.133.164", 5.7204203d, 10.901604d);
        User user8 = new User(396, "Terry", "Stowgill", "tstowgillaz@webeden.co.uk", "143.190.50.240", -6.7098551d, 111.3479498d);

        myMap.put(user1.getId(), user1);
        myMap.put(user2.getId(), user2);
        myMap.put(user3.getId(), user3);
        myMap.put(user4.getId(), user4);
        myMap.put(user5.getId(), user5);
        myMap.put(user6.getId(), user6);
        myMap.put(user7.getId(), user7);
        myMap.put(user8.getId(), user8);

        boolean actualReturn = myMap.toString().equals(distanceServiceImpl.getAllUsers().toString());
        boolean expectedReturn = true;

        assertEquals("test passed", expectedReturn, actualReturn);
    }

}
