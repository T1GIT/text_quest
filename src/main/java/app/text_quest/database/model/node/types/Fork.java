package app.text_quest.database.model.node.types;

import app.text_quest.database.model.Branch;
import app.text_quest.database.model.node.Node;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forks")
public class Fork extends Node {
    @OneToMany(mappedBy = "fork", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private final List<Branch> branches = new ArrayList<>();

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
