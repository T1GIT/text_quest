package app.text_quest.database.model.node.types;

import app.text_quest.database.model.Branch;
import app.text_quest.database.model.Limit;
import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Object-oriented representation for table <u>forks</u>
 * <p>
 * <b>Storages:</b>
 * Links to a possible branches
 * <p>
 * <b>Logic:</b>
 * When {@link User user} is on this stage, {@link Fork fork} provides possible {@link Branch branches}
 * including {@link Limit limits}, with help of them game can choose suitable {@link Branch branch} and
 * redirect {@link User user} into a next {@link Node node}.
 */
@Entity
@Table(name = "forks")
public class Fork extends Node {

    /**
     * Collection of available branches
     */
    @OneToMany(mappedBy = "fork", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private final List<Branch> branches = new ArrayList<>();

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
