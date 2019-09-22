package my.proofs;

import java.util.List;

public class Proof {
    private List<ProofLine> lines;

    public List<ProofLine> getLines() {
        return lines;
    }

    public Proof(List<ProofLine> lines) {
        this.lines = lines;
    }
}
