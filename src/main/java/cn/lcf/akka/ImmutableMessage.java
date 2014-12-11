package cn.lcf.akka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableMessage {
    private  int sequenceNumber;
    private  List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<String>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "ImmutableMessage{" +
                "sequenceNumber=" + sequenceNumber +
                ", values=" + values +
                '}';
    }

    public ImmutableMessage next(){
        return this;
    }
}