package com.example.myapplication.paillier;

import java.math.BigInteger;

public class PaillierTest {
    public static void main(String args[]){
        PaillierKeyGenerator keyGenerator = new PaillierKeyGenerator(512,64);
        PaillierPublicKey publicKey = keyGenerator.getPaillierPublicKey();
        PaillierPrivateKey privateKey = keyGenerator.getPaillierPrivateKey();
        BigInteger m1 = new BigInteger("20");
        BigInteger m2 = new BigInteger("80");
        PaillierEncryptor encryptor = new PaillierEncryptor(publicKey);
        PaillierDecryptor decryptor = new PaillierDecryptor(privateKey);
        BigInteger em1 = encryptor.encryptIt(m1);
        BigInteger em2 = encryptor.encryptIt(m2);
        System.out.println("After encrypted, m1 is "+ em1.toString());
        System.out.println("After encrypted, m2 is "+ em2.toString());
        System.out.println("After decrypted, em1 is " + decryptor.decryptIt(em1).toString());
        System.out.println("After decrypted, em2 is " + decryptor.decryptIt(em2).toString());
        PaillierCalculator calculator = new PaillierCalculator(publicKey);
        BigInteger sumEm1Em2 = calculator.add(em1,em2);
        System.out.println("em1 + em2 is "+sumEm1Em2.toString());
        System.out.println("After decrypted, em1 sum em2 is " + decryptor.decryptIt(sumEm1Em2).toString());
        BigInteger multipleEm1M2 = calculator.multiply(em1, m2);
        System.out.println("multipleEm1M2 is " + multipleEm1M2);
        System.out.println("After decrypted, multipleEm1M2 is " + decryptor.decryptIt(multipleEm1M2));
        System.out.println("publicKey: " + publicKey.getJsonStringPublicKey());
        System.out.println("privateKey: " + privateKey.getJsonStringPrivateKey());

        privateKey = PaillierPrivateKey.paillierJsonToPrivateKey("{\"n\":7037996759611275900405487329144489085210900622405788623915340046554895678557675360099993502545810105916795350348201798995744651664108236879690390748857833,\"nSquare\":49533398388298819693190911443085500113137594389227717398938303574532356291531019850234314622241175041250992063305927006862844026670633749958420794136365527887009273250901790502746504678689585917463571409706569379921923499464969602901871572009667889989146252127852333968575165007138552703354893437794045455889,\"g\":47,\"lambda\":879749594951409487550685916143061135651362577800723577989417505819361959819687634117238830220149011780391918958071436016289437839877979357110912709188800,\"timeStamp\":1580452220179}");
        PaillierDecryptor paillierDecryptor = new PaillierDecryptor(privateKey);
        System.out.println(paillierDecryptor.decryptIt(new BigInteger("40317556205695153350380068550907349679010189665168111502835738410488388511104339568920163437222170595333827897098453650789088957165737540804124359840030622193353486763878415408438220212433666405269417106352431078090539696346998735318967030477679650040334595951104993497595279094553250984035381799305120519160")));

    }
}
