package test.domain;

/**
 * Created by balajiravivarman on 08/10/2014.
 */
public class Line {

    private String name;
    private String statusSeverityDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription(String statusSeverityDescription) {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    @Override
    public String toString() {
        return "Line [name=" + name + ", statusSeverityDescription="
                + statusSeverityDescription + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!name.equals(line.name)) return false;
        if (!statusSeverityDescription.equals(line.statusSeverityDescription)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + statusSeverityDescription.hashCode();
        return result;
    }
}
