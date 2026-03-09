# Experiment-5-Creation-of-two-datacenters-with-one-host-each-and-run-two-cloudlets-on-them.-

 
---

## Overview
This project demonstrates a CloudSim 3.0.3 simulation where two users submit cloudlets that are executed across two separate datacenters. Each datacenter contains one host, and tasks are scheduled through a broker that distributes workloads to virtual machines.

This experiment illustrates multi-datacenter architecture, user-level task scheduling, and distributed cloud execution behavior.

---

## Aim
To create two datacenters with one host each and execute cloudlets from two users to analyze distributed execution behavior.

---

## Objectives
- Simulate multiple datacenters in CloudSim
- Configure hosts and allocate resources
- Simulate multiple users submitting tasks
- Create virtual machines for execution
- Analyze distributed execution results

---

## Tools and Technologies

| Tool | Version |
|------|---------|
| Java JDK | 1.8+ |
| CloudSim | 3.0.3 |
| IDE | IntelliJ / Eclipse |
| OS | Windows / Linux |

---

## System Configuration

### Datacenter Configuration

| Parameter | Value |
|----------|------|
| Datacenters | 2 |
| Hosts per DC | 1 |
| RAM | 2048 MB |
| Storage | 1,000,000 MB |
| Bandwidth | 10,000 Mbps |
| Scheduler | Time Shared |

---

### Virtual Machine Configuration

| Parameter | VM1 | VM2 |
|----------|-----|-----|
| MIPS | 500 | 500 |
| RAM | 512 MB | 512 MB |
| BW | 1000 Mbps | 1000 Mbps |
| VMM | Xen | Xen |
| Scheduler | Time Shared | Time Shared |

---

### Cloudlet Configuration

| Parameter | Value |
|----------|------|
| Users | 2 |
| Cloudlets | 4 |
| Lengths | 20000, 40000, 60000, 80000 |
| File Size | 300 MB |
| Output Size | 300 MB |
| PEs | 1 |

---
 
---

## Algorithm
1. Initialize CloudSim library  
2. Create two datacenters  
3. Configure one host per datacenter  
4. Create broker  
5. Create virtual machines  
6. Submit VM list to broker  
7. Create cloudlets with different lengths  
8. Submit cloudlets  
9. Start simulation  
10. Collect results  

---

## Execution Time Formula
Execution Time = Cloudlet Length / VM MIPS

## Program 



 

## Sample Output
========== RESULT ==========
Cloudlet 0 | Length: 20000 | VM: 0 | Datacenter: 2 | Time: 40.0
Cloudlet 1 | Length: 40000 | VM: 1 | Datacenter: 3 | Time: 80.0
Cloudlet 2 | Length: 60000 | VM: 0 | Datacenter: 2 | Time: 120.0
Cloudlet 3 | Length: 80000 | VM: 1 | Datacenter: 3 | Time: 160.0





---

## Result
The simulation successfully executed tasks submitted by multiple users across two datacenters. Cloudlets were scheduled based on available resources and executed accordingly.

---

## Conclusion
This experiment demonstrates that CloudSim effectively models distributed cloud environments. It shows how workloads are handled across multiple datacenters and validates distributed task execution concepts used in real cloud infrastructure.
