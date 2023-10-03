package model;

import com.brunomnsilva.smartgraph.graph.*;
import observerpattern.Subject;

import java.util.*;

public class UniversityNetwork extends Subject {

    private Graph<Person, Relationship> network;

    public UniversityNetwork() {
        network = new GraphEdgeList<>();
    }

    public Graph<Person, Relationship> getNetwork() {
        return network;
    }

    public void addPerson(Person person) throws UniversityNetworkException {
        if (findPerson(person.getId()) != null) {
            throw new UniversityNetworkException("the person with this id, already exists");
        }
        try {
            network.insertVertex(person);

            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new UniversityNetworkException();

        }
    }

    public void addClassRelationship(String description, int idTeacher, int idStudent) throws UniversityNetworkException {
        try {
            Vertex<Person> p1 = findPerson(idTeacher);
            Vertex<Person> p2 = findPerson(idStudent);
            if (p1 == null) {
                throw new UniversityNetworkException(" id " + idTeacher + " not exist");
            }
            if (p2 == null) {
                throw new UniversityNetworkException(" id " + idStudent + " not exist");
            }
            if (!p1.element().isRole(Person.PersonRole.TEACHER) || !p2.element().isRole(Person.PersonRole.STUDENT)) {
                throw new UniversityNetworkException(" Wrong type of Persons");
            }
            Relationship rel = new Relationship(description, Relationship.RelRole.CLASS);

            network.insertEdge(p1, p2, rel);

            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new UniversityNetworkException();
        }
    }

    public void addGroupRelationship(String description, int idStudent1, int idStudent2) throws UniversityNetworkException {
        try {
            Vertex<Person> p1 = findPerson(idStudent1);
            Vertex<Person> p2 = findPerson(idStudent2);
            if (p1 == null) {
                throw new UniversityNetworkException(" id " + idStudent1 + " not exist");
            }
            if (p2 == null) {
                throw new UniversityNetworkException(" id " + idStudent2 + " not exist");
            }
            if (!p1.element().isRole(Person.PersonRole.STUDENT) || !p2.element().isRole(Person.PersonRole.STUDENT)) {
                throw new UniversityNetworkException(" Wrong type of Persons");
            }
            Relationship rel = new Relationship(description, Relationship.RelRole.GROUP);

            network.insertEdge(p1, p2, rel);

            notifyObservers(null);
        } catch (InvalidVertexException e) {
            throw new UniversityNetworkException();
        }
    }

    public void removeRelation(int id1, int id2) throws UniversityNetworkException {

        try {
            Vertex<Person> p1 = findPerson(id1);
            Vertex<Person> p2 = findPerson(id2);

            for (Edge<Relationship, Person> edge : network.incidentEdges(p1)) {
                if (network.opposite(p1, edge) == p2) {
                    network.removeEdge(edge);
                }
            }

            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new UniversityNetworkException();
        }
    }

    public Collection<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        for (Vertex<Person> v : network.vertices()) {
            people.add( v.element() );
        }

        return people;
    }

    public void printTeachers() {
        System.out.println("Docentes");
        for (Vertex<Person> pV : network.vertices()) {
            if (pV.element().isRole(Person.PersonRole.TEACHER)) {
                System.out.println(pV.element().toString());
                for (Edge<Relationship, Person> edge : network.incidentEdges(pV)) {
                    Vertex<Person> v = network.opposite(pV, edge);
                    System.out.println(" \tde" + edge.element().toString() + " de " + v.element().toString());
                }
            }
        }
    }

    public Iterable<Person> getPersons(String groupName) {
        Set<Person> groupMembers = new HashSet<>();
        for (Edge<Relationship, Person> edge : network.edges()) {
            if (edge.element().getDescription().equalsIgnoreCase(groupName)) {
                if (edge.element().isRole(Relationship.RelRole.CLASS)) {
                    throw new UniversityNetworkException(" Wrong type of Relationship");
                }
                Person p1 = edge.vertices()[0].element();
                Person p2 = edge.vertices()[1].element();
                groupMembers.add(p1);
                groupMembers.add(p2);
            }
        }
        return groupMembers;
    }

    public void printGroup(String groupName) {
        System.out.println("GRUPO " + groupName);
        for (Person p : getPersons(groupName)) {
            System.out.println("\t" + p);
        }

    }

    public int getNumberOfStudents(int id) throws UniversityNetworkException {
        Vertex<Person> vPerson = findPerson(id);
        if (vPerson.element().isRole(Person.PersonRole.STUDENT)) {
            throw new UniversityNetworkException(" Wrong argument is not a teacher");
        }
        return ((Collection) network.incidentEdges(vPerson)).size();

    }

    public Person getMostPopular() {
        if (network.numVertices() == 0) {
            return null;
        }
        Vertex<Person> popular = null;
        int max = -1;
        for (Vertex<Person> vPerson : network.vertices()) {
            int numberContacts = ((Collection) network.incidentEdges(vPerson)).size();
            if (numberContacts > max) {
                popular = vPerson;
                max = numberContacts;
            }
        }
        return popular.element();
    }

    public boolean personExists(String name) {
        for (Vertex<Person> v : network.vertices()) {
            if (v.element().getName() == name) {
                return true;
            }
        }
        return false;
    }


    public List<Person> getIsolated() {
        List<Person> isolados = new ArrayList<>();
        if (network.numVertices() == 0) {
            return null;
        }
        for (Vertex<Person> vPerson : network.vertices()) {
            int numberContacts = ((Collection) network.incidentEdges(vPerson)).size();
            if (numberContacts == 0) {
                isolados.add(vPerson.element());
            }
        }
        return isolados;
    }

    public List<Relationship> getRelationShip(int id1, int id2) throws UniversityNetworkException {
        List<Relationship> relations = new ArrayList<>();

        Vertex<Person> p1 = findPerson(id1);
        Vertex<Person> p2 = findPerson(id2);
        if(p1==null || p2==null) throw new UniversityNetworkException(" Not a valid ID ");

        for (Edge<Relationship, Person> edge : network.incidentEdges(p1)) {
            if (network.opposite(p1, edge) == p2) {
                relations.add(edge.element());
            }
        }
        return relations;
    }

    private Vertex<Person> findPerson(int id) {
        for (Vertex<Person> v : network.vertices()) {
            if (v.element().getId() == id) {
                return v;
            }
        }
        return null;
    }

    public void removePerson(int id) throws UniversityNetworkException {
        Vertex<Person> personVertex = findPerson(id);
        if(personVertex == null){
            throw new UniversityNetworkException("No person with that id exists");
        }
        network.removeVertex(personVertex);
        notifyObservers(this);
    }
}
