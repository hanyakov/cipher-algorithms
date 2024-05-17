package main;

public class CipherTiming implements Comparable<CipherTiming> {
    String cipherName;
    long time;

    public CipherTiming(String cipherName, Long time) {
        this.cipherName = cipherName;
        this.time = time;
    }

    @Override
    public int compareTo(CipherTiming other) {
        return Long.compare(this.time, other.time);
    }

    @Override
    public String toString() {
        return cipherName + ": " + time + " ns";
    }
}
