# Object-Oriented Programming

**Distribution Center - Phase One**

**School Year: 2022/2023**

**Normal Season**

# 1. Introduction

The objective of this project is to develop a simulation of the operation of a product distribution center using the Java language and Object-Oriented Programming (OOP).

In this distribution center, products are received at a specific location in the warehouse, are packaged, and are then transported to the locations where they are stored. The transportation is done by Automated Guided Vehicles (AGV). The distribution of the products to the sales locations follows an identical process where stored products are taken from the storage locations and taken to a pick-up location within the warehouse. 

The project consists of modeling and implementing a set of classes that allow you to represent the logic of the application and that use the **console** as a form of visualization. 

Pay particular attention to the use of the OOP paradigm in modeling the classes, their components and the relationships established between them, i.e., the correct use of the concepts of encapsulation, inheritance, abstract classes, polymorphism, interfaces, maximizing cohesion (single responsibility), minimizing coupling, responsibility-oriented design, etc. A well thought-out modeling will facilitate the entire development and maintenance process.

This document covers only the first phase of the project. In due course, after delivery of the first phase, the statement of the second phase will be presented.

> **Note**:  The interpretation of the problem is an integral part of the assessment.  Any questions regarding this statement that were not seen with the lab teacher should be resolved using the good practices covered in class.

# 2. Distribution Center

The distribution center is a rectangular space that will hold all the elements of the simulation. This space is divided into positions, where each of them represents a 2x2m square area.  The existing elements can be storage places with shelves/ racks to store the products, vehicles that circulate in the warehouse, or the walls that limit the warehouse. In addition, there are spaces for loading and unloading the products that the warehouse contains and areas in front of the internal storage places to deposit and collect the products. 

## 2.1 Products

For this simulation, products are very simple, characterized only by a name, a unique identifier, their weight and their type. In this case, the following types of products will be considered: clothing, accessories, small and large toys, small and large electronic equipment, and books. 

Incoming products are usually packaged before being stored. Therefore, the following types of packaging have been defined:

- **Bag** - used to store a small lightweight product, namely accessories, toys or small equipment and clothing;
- **Box** - used to store a product just like bags with the difference that it is not used to store clothes, but can be used to store a book;
- **Cardboard box** - can hold up to 10 products that are not large. Alternatively, it can be used to store a single large product such as a toy or large electronic equipment.
- **Pallet** - can hold multiple cartons. 

All packaging types have an entire code that uniquely identifies them. Identification of packages is done by an abbreviation for the type of package and its code.

## 2.2 Pickup and Delivery Locations

Each warehouse has two separate locations for product pickup and delivery from the outside. These locations are located on opposite walls of the warehouse occupying 50% of that wall space. For example, there may be on the left wall a place for delivering products to storage and on the right wall a place for picking products from the warehouse. These locations can be defined by replacing the wall with an open pickup space with a point width (a line with the thickness of a position as mentioned before).

## 2.2 Storage Locations

Storage locations are shelves consisting of rectangular areas within the warehouse that can hold any type of product without limit. The only restriction is that stored products must be in a package. In turn, there can be pallets with several cartons of products. These places have a space in front of one of the longest sides with a thickness of one position that occupies the entire side. This space is used by the vehicles to deposit and collect the packages from the respective location.

## 2.3 Automatically guided vehicles (AGV)

Vehicles are used inside the warehouse to transport products within the warehouse. These vehicles can follow predetermined paths or define their own routes based on the pick location and the destination location of the product. To help them on their way, they can rely on different types of sensors.  

### 2.3.1 Types of Vehicles

Depending on the type of product being transported, different types of vehicles can be used. Therefore, the following types of vehicles are considered:

- **Unit Load Carrier** (Unit Load Carrier - ULC) - This is an AGV used to transport a single pallet. It has no weight limits for the load carried;
- **Automatic Guided Card (AGC)** - An AGV used to transport light products, usually in bags or boxes; it has a total weight limit of 100 kg.
- **Towing Vehicle** - An AGV that does not directly transport products. In this case, it is used in the simulation to tow a transport cart.
- **Transport Trolley** - It is not an AGV. It is designed to transport boxes or bags up to a weight limit of 200 kg and is pulled by a towing vehicle.

Each of these vehicles occupies a position in the warehouse.  

### 2.3.2 Vehicle Movement

The movement of the vehicles inside the warehouse is done exclusively in the horizontal direction or in the vertical direction. Each of them moves one position for each step of the simulation, and can turn or stop. In the specific case of the ULC, it is possible to increase the speed up to a maximum of 3 positions per simulation step. The speed increase of this vehicle is a maximum of one position per simulation step. Stopping is done immediately without the need for deceleration. 

The ULC is the only AGV vehicle that cannot automatically determine its paths. In this case, it follows a predetermined path. The other AGV vehicles can establish their path between two positions in an automatic way.   

### 2.3.3 Types of Sensors

All the AGVs described have 3 types of sensors with different characteristics. Therefore the following sensor types have been defined:

- **Lidar** (Light Detection and Ranging) - It is a sensor that allows determining which positions in the warehouse are occupied or empty. It only obtains the information up to a limit of 20 meters in the direction of movement of the vehicle on which it is mounted and in a vision angle of 30º.
- **Ultrasonic Sensor** - It is identical to Lidar, but has an angle of view of 180º and a range of 8 meters;
- **Camera** - The camera is used to recognize the elements of the warehouse. It can identify the elements up to a distance of 30 meters with an angle of view of 90º. It has no information about the distance of the identified elements and cannot see beyond the first position that is occupied in a given direction in its field of view. So it is usually used with information from Lidar or the ultrasonic sensor to identify the first non-empty point in a given direction. In this case, it lets you know if the point corresponds to a vehicle, a storage location, or a wall. ### 

## 3. simulation

For this simulation a warehouse must be defined. Vehicles and storage locations can then be added to the warehouse created. During the simulation, products will be delivered that must be packed and stored. A selection of packaging and vehicles must be made to transport the products to their storage location. In the same way, several products can be ordered among those existing in the warehouse. In this case, the same vehicles can be used to pick up the products and drop them off at the delivery points. 

The simulation data should be provided in "CSV" format files that must be read by the simulator to be used in the preparation of the simulation and during its execution. These files have the information about the warehouse size, the list of storage locations with their respective positions and dimensions, and the list of available vehicles. For the simulation there should be a file with the list of products delivered for storage and the step of the simulation in which this happens. It should also include the list of products to be picked from the warehouse with the time when this happens.

During the simulation there should be log messages in the console about what is happening.

# 5. development and delivery phases

The project is divided into 2 phases, with the quotation distributed as follows:

- Phase I - 70% of the final assessment
- Phase II - 30% of the final grade

As stated in the course sheet, you can alternatively deliver the project in a single date, in the appeal period (without the possibility of incorporating the continuous assessment component), contemplating the two phases of the project.


# 6. implementation and coding

The program should be developed using the Java language, putting into practice the fundamental concepts of the Object-Oriented Programming paradigm.

Regarding the coding rules, follow the conventions normally adopted for the Java language:

- The _camelCase_ notation for the name of local variables and identifiers for attributes and methods;
- The _PascalCase_ notation for class and interface names;
- Use of uppercase for the names of constants and enumerated values;
- Do not use the &#39;\_&#39; symbol in identifiers (except in constants), or abbreviations.

It is necessary that the project complies with what is asked for in its statement, leaving any implementation detail that is not mentioned to the programmer's discretion, which should be properly documented.

You must implement: Collections, Inheritance, Polymorphism, Interfaces, Exceptions, and Input and Output.

# 7. group constitution

Each project must be developed in groups of two students, and may eventually be developed individually. Under no circumstances will groups of more than two students be allowed.

The student groups are already determined through the _pair programming_ methodology that is being used in the labs. If there are students who do not have their group chosen, they should contact their lab teacher to regularize the situation.

# 8. project delivery

The project will be delivered in two phases:

- A first phase ( **by 08:00:00 on May 30, 2023** ) with the implementation of the application logic (described in this document);
- A second phase ( **until 08:00:00 on June 26, 2023** ) with the graphic part (described in a document to be delivered in the future).

The project must be delivered by the specified deadline **only electronically using the groups created on Github**.

**Any projects submitted after the deadline will not be accepted!

All project materials must be properly labeled with the students' name, number, and email address.

Project materials should include:

- A **Technical Manual** containing a brief description of the program, including an explanation of the implemented classes/interfaces, main attributes and methods, and their relationships.
- The program documentation in **JavaDoc** (do not convert the automatically generated HTML document to DOC!).
- The source code of the program in project form in _NetBeans_ or _IntelliJ_, with a working test _main_ and all implemented functionality.
- All the files that make up the project must be stored in a single compressed file in ZIP format whose name must have the following nomenclature: <course>_<numStudent1>_<numStudent2>.zip.

## 9. Project Evaluation Rules and Criteria

## 9.1 Evaluation Rules

The evaluation of the project is subject to the following rules:

- If the student misses the supervision moment, he/she will have that component evaluated with zero values.
- **No projects delivered after the deadline will be accepted!**
- The program classification will take into account the quality of the programming (software quality factors), the structure of the code created according to the principles of Object Oriented Programming, taking into account concepts such as cohesion of classes and methods, the degree of coupling between classes and responsibility-oriented class design, and the use/knowledge of the Java language.
- User-friendliness, presentation, imagination and creativity will be rewarded.
- The project will have a mandatory oral evaluation component with individual grading of the group members.
- Students who do not attend the discussion will be scored zero in the respective phase. This discussion will assess the student's ability to produce the code presented. In cases where this ability is not demonstrated, the grade will be zero.
- The oral evaluation is performed by the respective lab teacher and will be scheduled in advance for each work group.
- All projects will be submitted to an automatic copy detection system. Projects that are identified as possible copies, and if they are indeed copies, will be cancelled.
- Evaluations of the first phase of the project will take place in the first week of June 2023.

## 9.2 Evaluation Criteria

The first phase of the project will be evaluated according to the following criteria:

| **Functionalities** | **45%** |
| ------------------- | ------- |

| **Implementation** | **35%** |
| ------------------------------------------- | ------- |
| Class Structure 20
| Language Knowledge and Good Language Usage 5% of the language
| Good Styling (names, comments, indentation) | 5% |
| Unit Tests Definition 5% | 5% |

| **Documentation** | **10%** |
| ---------------- | ------- |
| JavaDOC documentation
| Technical Manuals

| **Qualitative evaluation** | **10%** |
| ------------------------- | ------- |

## 10. Summary of Important Dates

## 10.1 Phase 1 Delivery

Delivery of the 1st phase of the project will be by **at 08:00 AM on Monday, May 30, 2023.**

## 10.2 Phase I Evaluations

Evaluations of the first phase of the project will take place the week of **May 30 to June 3, 2023**.