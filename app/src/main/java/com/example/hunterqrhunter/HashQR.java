package com.example.hunterqrhunter;
// This needs to be inside model folder since it is a QR model
public class HashQR {
    public static int hashObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        int hash = obj.hashCode();
        if (hash == Integer.MIN_VALUE) {
            hash = 0;
        } else if (hash < 0) {
            hash = -hash;
        }
        return hash;
    }
}
