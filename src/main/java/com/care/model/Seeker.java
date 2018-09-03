
package com.care.model;

public class Seeker {

        private int id;
        private int numberOfChildren;
        private String spouseName;
        private Member member;

        public static final Seeker EMPTY_SEEKER = new Seeker();

        public Member getMember(){
                return member;
        }
        public void setMember(Member member){
                this.member = member;
        }

        public int getNumberOfChildren() {
                return numberOfChildren;
        }

        public void setNumberOfChildren(int numberOfChildren) {
                this.numberOfChildren = numberOfChildren;
        }

        public String getSpouseName() {
                return spouseName;
        }

        public void setSpouseName(String spouseName) {
                this.spouseName = spouseName;
        }

    @Override
    public String toString() {
        return "Seeker{" +
                "id=" + id +
                ", numberOfChildren=" + numberOfChildren +
                ", spouseName='" + spouseName + '\'' +
                '}';
    }
}
