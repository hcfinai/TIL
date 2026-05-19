package week4.policy;

public class StaffPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}