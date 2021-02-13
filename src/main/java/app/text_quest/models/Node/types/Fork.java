package app.text_quest.models.Node.types;

import app.text_quest.models.Branch;
import app.text_quest.models.Node.Node;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "forks")
public class Fork extends Node {
    @OneToMany(mappedBy = "fork", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Branch> branches;

    public Fork() { }

    public List<Branch> getBranches() {
        return branches;
    }

    public void addBranch(Branch branch) {
        this.branches.add(branch);
        branch.setFork(this);
    }

    public void removeBranch(Branch branch) {
        this.branches.remove(branch);
        branch.setFork(null);
    }

    @Override
    public String toString() {
        return "Fork{" +
                "branches=" + branches +
                '}';
    }
}
