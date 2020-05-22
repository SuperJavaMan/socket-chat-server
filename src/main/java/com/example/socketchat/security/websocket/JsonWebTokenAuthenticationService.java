//package com.example.socketchat.security.websocket;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {
//
//    @Value("security.token.secret.key")
//    private String secretKey;
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public JsonWebTokenAuthenticationService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Authentication authenticate(HttpServletRequest request) {
//        final String token = request.getHeader("x-auth-token");
//        final Jws<Claims> tokenData = parseToken(token);
//
//        if (Objects.nonNull(tokenData)) {
//            User user = getUserFromToken(tokenData);
//            if (Objects.nonNull(user) && user.isEnabled()) {
//                return new UserAuthentication(user);
//            }
//        }
//        return null;
//    }
//
//    public Authentication getUserFromToken(String token) {
//        if (Objects.isNull(token))
//            return null;
//
//        final Jws<Claims> tokenData = parseToken(token);
//
//        if (Objects.nonNull(tokenData)) {
//            User user = getUserFromToken(tokenData);
//            if (Objects.nonNull(user) && user.isEnabled()) {
//                return new UserAuthentication(user);
//            }
//        }
//        return null;
//    }
//
//    private Jws<Claims> parseToken(final String token) {
//        if (Objects.nonNull(token)) {
//            try {
//                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
//                    | SignatureException | IllegalArgumentException e) {
//                log.warn("Token parse failed", e);
//                return null;
//            }
//        }
//        return null;
//    }
//
//    private User getUserFromToken(final Jws<Claims> tokenData) {
//        try {
//            return userRepository.findByUsername(tokenData.getBody().get("username").toString());
//        } catch (UsernameNotFoundException e) {
//            log.warn("No user", e);
//        }
//        return null;
//    }
//}
