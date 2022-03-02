package me.q1zz.geoip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class GetCountry {

    public static String getCountry(String ipv4) throws IOException, GeoIp2Exception {
        try (InputStream in = GetCountry.class.getResourceAsStream("/GeoLite2-Country.mmdb")) {

            DatabaseReader reader = new DatabaseReader.Builder(in).build();
            InetAddress ipAddress = InetAddress.getByName(ipv4);

            CountryResponse response = reader.country(ipAddress);
            Country country = response.getCountry();

            return country.getIsoCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
