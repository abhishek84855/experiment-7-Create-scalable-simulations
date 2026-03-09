 #  EXPERIMENT 7 CloudSim Multi-Datacenter Simulation

## Overview
This project demonstrates a **CloudSim 3.0.3 simulation** where two users submit cloudlets that are executed across **two separate datacenters**. Each datacenter contains one host, and tasks are scheduled through a broker that distributes workloads to virtual machines.

This experiment illustrates **multi-datacenter architecture, user-level task scheduling, and distributed cloud execution behavior**.

---

## Aim
To create **two datacenters with one host each** and execute cloudlets from **two users** to analyze distributed execution behavior.

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

 ## Program flow 

 <img width="1024" height="1536" alt="image" src="https://github.com/user-attachments/assets/fdb0238c-1ae5-41ff-9648-321aac189b36" />


## Program


 ## Output 




## Result

The simulation successfully executed tasks submitted by multiple users across two datacenters. Cloudlets were scheduled based on available resources and virtual machine allocation and executed accordingly.
