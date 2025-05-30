package system.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtils {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60;
    private static final String SECRET = "09de29edcc733033413a859e15124696";


    /**
     *
     * @param token
     * @return
     */
   public static boolean verify(String token){
       try {
           Algorithm algorithm = Algorithm.HMAC256(SECRET);
           JWTVerifier verifier = JWT.require(algorithm).build();
           DecodedJWT jwt= verifier.verify(token);
           return true;
       } catch (Exception e) {
           return false;
       }
   }

   public  static Long getUserId(String token){
       try {
           DecodedJWT jwt = JWT.decode(token);
           return jwt.getClaim("userId").asLong();
       } catch (JWTDecodeException e) {
           return null;
       }
   }

   public static String sign(Long id){
       try {
           Date date = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
           Algorithm algorithm = Algorithm.HMAC256(SECRET);
           return JWT.create()
                   .withClaim("id",id)
                   .sign(algorithm);
       } catch (Exception e) {
           throw null;
       }
   }
}
