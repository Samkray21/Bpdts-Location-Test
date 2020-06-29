package com.bpdts.location;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.util.GeometricShapeFactory;
import java.util.HashMap;
import java.util.Map;

public class DistanceServiceImpl implements DistanceService{

    private static final Polygon londonCircle = createCircle(51.502671d, -0.144591d, 53108.4);
    //circle that covers london and 50 miles within London
    private static final Polygon LondonAndFiftyCircle = createCircle(51.502671d, -0.144591d, 214043);
    private BpdtsConnector bpdtsConnector = new BpdtsConnector();

    public Map<Integer, User> getAllUsers(){
        Map<Integer,User> allUsersMap = new HashMap<>();
        String allUsersResponse = bpdtsConnector.getResponse("users");
        String londonResponse = bpdtsConnector.getResponse("city/London/users");
        allUsersMap = parseAllUsers(allUsersResponse, allUsersMap);
        return parseLondonUsers(londonResponse, allUsersMap);
    }

    private static Polygon createCircle(double latitude, double longitude, double diameterInMeters){
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(64);
        shapeFactory.setCentre(new Coordinate(latitude, longitude));
        // Length in meters of 1° of latitude = always 111.32 km
        shapeFactory.setWidth(diameterInMeters/111320d);
        // Length in meters of 1° of longitude = 40075 km * cos( latitude ) / 360
        shapeFactory.setHeight(diameterInMeters / (40075000 * Math.cos(Math.toRadians(latitude)) / 360));

        return shapeFactory.createEllipse();
    }

    private boolean checkCoordinate(Polygon circle, double latitude, double longitude) throws ParseException {
        String pointInString = String.format("POINT (%f %f)", latitude, longitude);

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        WKTReader reader = new WKTReader(geometryFactory);
        Point point = (Point) reader.read(pointInString);

        return circle.contains(point);
    }

    private boolean withinFiftyOfLondon(double latitude, double longitude) throws ParseException {
        if (!checkCoordinate(londonCircle, latitude, longitude) && checkCoordinate(LondonAndFiftyCircle, latitude, longitude)){
            return true;
        }else{
            return false;
        }
    }

    private Map<Integer, User> parseAllUsers(String responseBody, Map<Integer, User> map) {
        JSONArray userInfos = new JSONArray(responseBody);
        for (int i = 0; i < userInfos.length(); i++){
            User user = getUser(userInfos, i);

            try {
                if (withinFiftyOfLondon(user.getLatitude(), user.getLongitude())){
                    map.put(user.getId(), user);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    private Map<Integer, User> parseLondonUsers(String responseBody, Map<Integer, User> map) {
        JSONArray userInfos = new JSONArray(responseBody);
        for (int i = 0; i < userInfos.length(); i++){
            User user = getUser(userInfos, i);
            map.put(user.getId(), user);
        }
        return  map;
    }

    private User getUser(JSONArray userInfos, int i) {
        JSONObject userInfo = userInfos.getJSONObject(i);
        int id = userInfo.getInt("id");
        String firstName = userInfo.getString("first_name");
        String lastName = userInfo.getString("last_name");
        String email = userInfo.getString("email");
        String ipAddress = userInfo.getString("ip_address");
        Double latitude = userInfo.getDouble("latitude");
        Double longitude = userInfo.getDouble("longitude");
        return new User(id, firstName, lastName, email, ipAddress, latitude, longitude);
    }




}
