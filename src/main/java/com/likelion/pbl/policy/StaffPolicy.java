package com.likelion.pbl.policy;

public class StaffPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}
