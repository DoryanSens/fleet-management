package fr.extia.mentoring.fleetmanagement.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ADR.class, fetch = FetchType.EAGER)
    @CollectionTable(name="driver_auth", joinColumns = @JoinColumn(name="driver_id"))
    private Set<ADR> accreditations;

    public Driver(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Driver() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ADR> getAccreditations() {
        return accreditations;
    }

    public void setAccreditations(Set<ADR> accreditations) {
        this.accreditations = accreditations;
    }

    public void addAccreditation(ADR adr) {
        if(null==accreditations){
            this.accreditations = new HashSet<>();
        }
        this.accreditations.add(adr);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
