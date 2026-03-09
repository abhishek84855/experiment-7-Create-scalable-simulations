import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;

import org.cloudbus.cloudsim.core.CloudSim;

import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import java.util.*;

public class ScalableSimulation {

    private static List<Vm> vmList;
    private static List<Cloudlet> cloudletList;

    public static void main(String[] args) {

        try {

            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean traceFlag = false;

            CloudSim.init(numUsers, calendar, traceFlag);

            // ---------- SCALABLE PARAMETERS ----------
            int numDatacenters = 2;
            int hostsPerDatacenter = 2;
            int numberOfVMs = 2;
            int numberOfCloudlets = 10;

            // ---------- CREATE DATACENTERS ----------
            for (int i = 0; i < numDatacenters; i++) {
                createDatacenter("Datacenter_" + i, hostsPerDatacenter);
            }

            // ---------- CREATE BROKER ----------
            DatacenterBroker broker = createBroker();
            int brokerId = broker.getId();

            // ---------- CREATE VMs ----------
            vmList = new ArrayList<>();

            for (int i = 0; i < numberOfVMs; i++) {
                int mips = 1000 + (i * 200);
                long size = 10000;
                int ram = 1024;
                long bw = 1000;
                int pesNumber = 1;
                String vmm = "Xen";

                Vm vm = new Vm(i, brokerId, mips, pesNumber, ram, bw, size,
                        vmm, new CloudletSchedulerTimeShared());

                vmList.add(vm);
            }

            broker.submitVmList(vmList);

            // ---------- CREATE CLOUDLETS ----------
            cloudletList = new ArrayList<>();
            UtilizationModel utilizationModel = new UtilizationModelFull();

            for (int i = 0; i < numberOfCloudlets; i++) {

                long length = 40000 + (i * 5000);
                long fileSize = 300;
                long outputSize = 300;
                int pesNumber = 1;

                Cloudlet cloudlet = new Cloudlet(i, length, pesNumber,
                        fileSize, outputSize,
                        utilizationModel,
                        utilizationModel,
                        utilizationModel);

                cloudlet.setUserId(brokerId);
                cloudletList.add(cloudlet);
            }

            broker.submitCloudletList(cloudletList);

            // ---------- START SIMULATION ----------
            CloudSim.startSimulation();

            List<Cloudlet> newList = broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            printCloudletList(newList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------
    // CREATE DATACENTER
    // -----------------------------------------------------
    private static Datacenter createDatacenter(String name, int hostsCount) {

        List<Host> hostList = new ArrayList<>();

        for (int i = 0; i < hostsCount; i++) {

            List<Pe> peList = new ArrayList<>();

            int mips = 1000;

            for (int j = 0; j < 4; j++) {
                peList.add(new Pe(j, new PeProvisionerSimple(mips)));
            }

            int hostId = i;
            int ram = 8192;
            long storage = 1000000;
            int bw = 10000;

            hostList.add(
                    new Host(
                            hostId,
                            new RamProvisionerSimple(ram),
                            new BwProvisionerSimple(bw),
                            storage,
                            peList,
                            new VmSchedulerTimeShared(peList)
                    )
            );
        }

        String arch = "x86";
        String os = "Linux";
        String vmm = "Xen";
        double timeZone = 10.0;
        double costPerSec = 3.0;
        double costPerMem = 0.05;
        double costPerStorage = 0.001;
        double costPerBw = 0.0;

        DatacenterCharacteristics characteristics =
                new DatacenterCharacteristics(
                        arch, os, vmm, hostList,
                        timeZone, costPerSec,
                        costPerMem, costPerStorage,
                        costPerBw);

        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics,
                    new VmAllocationPolicySimple(hostList),
                    new LinkedList<Storage>(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    // -----------------------------------------------------
    // CREATE BROKER
    // -----------------------------------------------------
    private static DatacenterBroker createBroker() {

        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return broker;
    }

    // -----------------------------------------------------
    // PRINT RESULTS
    // -----------------------------------------------------
    private static void printCloudletList(List<Cloudlet> list) {

        System.out.println("\n========== OUTPUT ==========");
        System.out.println("Cloudlet ID\tStatus\tVM ID\tTime\tStart\tFinish");

        for (Cloudlet cloudlet : list) {

            System.out.print(cloudlet.getCloudletId() + "\t\t");

            if (cloudlet.getStatus() == Cloudlet.SUCCESS) {
                System.out.print("SUCCESS\t");
                System.out.println(
                        cloudlet.getVmId() + "\t" +
                                cloudlet.getActualCPUTime() + "\t" +
                                cloudlet.getExecStartTime() + "\t" +
                                cloudlet.getFinishTime()
                );
            }
        }
    }
}