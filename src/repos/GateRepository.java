package repos;
import models.Gate;
import models.GateStatus;
import models.GateType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private Map<Long, Gate> gates = new HashMap();
    private int gate_number =123;

    public Optional<Gate> findGateById(Long id) {

        if(gates.containsKey(id)) {
            Gate gate = gates.get(id);
            return Optional.of(gate);
        }
        Gate gate = new Gate();
        gate.setGateType(GateType.ENTRY);
        gate.setGateNumber(gate_number);
        gate.setId(id);
        gate.setGateStatus(GateStatus.WORKING);
        gate_number++;
        gates.put(id, gate);
        return Optional.of(gate);
    }

}
