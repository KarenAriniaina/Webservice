package Tp.model;

import java.util.Base64;
import java.util.Date;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import Tp.dao.ObjetBDD;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Personne extends ObjetBDD {
    private String idPersonne;
    private String nom;
    private String mail;
    private String passwords;
    private String Token;

    public String getToken() {
        if (Token == null)
            this.setToken(this.getJWTToken());
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Personne() {
        this.setNomTable("Personne");
        this.setPrimaryKey("idPersonne");
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String password) {
        this.passwords = password;
    }

    private String getJWTToken() {
        Instant now = Instant.now();
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
                String jwtToken = Jwts.builder()
                .claim("idPersonne", this.getIdPersonne())
                .setSubject(this.getIdPersonne())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(2, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256,hmacKey)
                .compact();
        return "Bearer " + jwtToken;
    }

    public boolean VerifToken() {
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        Jws<Claims> jwt =null;
        try{
            jwt= Jwts.parser()
                .setSigningKey(hmacKey)
                .parseClaimsJws(this.getToken().replaceFirst("Bearer ", ""));
        }catch(io.jsonwebtoken.ExpiredJwtException ex){
            jwt=null;
        }
        if(jwt!=null) return true;
        return false;
    }

    /*public static void main(String[] args) throws Exception {
        Personne p = new Personne();
        p.setIdPersonne("Personne_1");
        //p.setToken("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSmFuZSBEb2UiLCJlbWFpbCI6ImphbmVAZXhhbXBsZS5jb20iLCJzdWIiOiJqYW5lIiwianRpIjoiYTcyMjZlN2EtMDdkNS00NjdmLTk4MzAtYzVjODg5MTAyZjcwIiwiaWF0IjoxNjcxMTAzODc4LCJleHAiOjE2NzExMDQxNzh9.Q5mYZ4TvNzeLjle9jpIZ7xj6p1gnWgxAHqZKfmiGM0I");
        //Jws<Claims> jwt = p.parseJwt();
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSmFuZSBEb2UiLCJlbWFpbCI6ImphbmVAZXhhbXBsZS5jb20iLCJzdWIiOiJqYW5lIiwianRpIjoiYTcyMjZlN2EtMDdkNS00NjdmLTk4MzAtYzVjODg5MTAyZjcwIiwiaWF0IjoxNjcxMTAzODc4LCJleHAiOjE2NzExMDQxNzh9.Q5mYZ4TvNzeLjle9jpIZ7xj6p1gnWgxAHqZKfmiGM0I
        System.out.println(p.VerifToken());
        //System.out.println(p.getToken());
        //System.err.println(p.VerifierToken("Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSmFuZSBEb2UiLCJlbWFpbCI6ImphbmVAZXhhbXBsZS5jb20iLCJzdWIiOiJqYW5lIiwianRpIjoiODNmZTUyNzQtMWMxOS00MTc0LThjZDYtMTk3MzlhZmYwNDViIiwiaWF0IjoxNjcxMTA0MTA5LCJleHAiOjE2NzExMDQ0MDl9.Pt8iy4S9pDLAkNd49sMSOYW6ZEQcikquY6W3NK95vJc"));
    }*/
}