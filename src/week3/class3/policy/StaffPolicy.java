package week3.class3.policy;

public class StaffPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}