package domain;

import cat.tecnocampus.rooms.domain.exceptions.ClassroomOccupiedException;
import cat.tecnocampus.rooms.domain.exceptions.StudentAlreadyAllocatedException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private String name;
    private int capacity;
    private String orientation;
    private boolean plugs;
    private List<Allocation> allocations = new ArrayList<>();

    public Classroom() {

    }

    // Follows the builder patter. Useful when a constructor has lots of different non mandatory attributes
    public Classroom(ClassroomBuilder builder) {
        name = builder.name;
        capacity = builder.capacity;
        orientation = builder.orientation;
        plugs = builder.plugs;
        allocations = new ArrayList<>();
    }

    public Classroom copyClassroomNoAllocations() {
        Classroom classroomCopy = new Classroom();
        classroomCopy.allocations = null;
        classroomCopy.name = this.name;
        classroomCopy.capacity = this.capacity;
        classroomCopy.orientation = this.orientation;
        classroomCopy.plugs = this.plugs;

        return classroomCopy;
    }

    public int getOccupation(DayOfWeek dayOfWeek) {
        long occupation;
        occupation = allocations.stream().filter(d -> d.getDayOfWeek().equals(dayOfWeek)).count();
        return Math.toIntExact(occupation);
    }

    public boolean isFull(DayOfWeek dayOfWeek) {
        return getOccupation(dayOfWeek) >= capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getOrientation() {
        return orientation;
    }

    public boolean isPlugs() {
        return plugs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setPlugs(boolean plugs) {
        this.plugs = plugs;
    }

    public void allocate(Student student, DayOfWeek dayOfWeek) {
        if (isFull(dayOfWeek)) {
            throw new ClassroomOccupiedException(this.name, dayOfWeek.name());
        } else if (isStudentAllocated(student, dayOfWeek)) {
            throw new StudentAlreadyAllocatedException(this.name, student.getName(), dayOfWeek.name());
        }
        allocations.add(new Allocation(student, dayOfWeek));
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    private boolean isStudentAllocated(Student student, DayOfWeek dayOfWeek) {
        return allocations.stream().filter(a -> a.getDayOfWeek().equals(dayOfWeek))
                .anyMatch(a -> a.getStudent().getId().equals(student.getId()));
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", orientation='" + orientation + '\'' +
                ", plugs=" + plugs +
                '}';
    }

    public static class ClassroomBuilder {
        public String name;
        public int capacity;
        public String orientation;
        public boolean plugs;

        public ClassroomBuilder() {
        }

        public ClassroomBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClassroomBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public ClassroomBuilder orientation(String orientation) {
            this.orientation = orientation;
            return this;
        }

        public ClassroomBuilder plugs(boolean plugs) {
            this.plugs = plugs;
            return this;
        }

        public Classroom build() {
            return new Classroom(this);
        }
    }
}
