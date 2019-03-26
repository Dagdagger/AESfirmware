package stream18.aescp.model;

import java.util.Hashtable;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestPhase.Phase;

// Each test is composed of a series of steps (phases).
// On each phase we need to do the same:
// 1. one or more valves are set to a specific state
// 2. Once the valves have been setup as required, we'll
// 	  start acquiring data
// 3. we will loop acquiring and logging data until the
//    phase completion time elapses
// 4. Once the time elapses, we do the transition to next phase
// 
// All of these steps must be configurable, and associated to
// a specific TestMode
//
// That way, we should have, for example:
//
//		- TestModeCfg[1].PhaseCfg[3].valves[0] = 0
//		- TestModeCfg[1].PhaseCfg[3].valves[1] = 1
//		- TestModeCfg[1].PhaseCfg[3].valves[2] = 0
//		- TestModeCfg[1].PhaseCfg[3].phaseTimeMs = 100
//		- TestModeCfg[1].PhaseCfg[3].adq[0] = 1
//		- TestModeCfg[1].PhaseCfg[3].adq[1] = 
//		- TestModeCfg[1].PhaseCfg[3].nextPhase = 4
//
//	Also, some values are configured dynamically, with values from the forms
//	But, an important question here is: how do we map the values in the forms
//  into test values?

// Defines the configuration parameters for a specific TestMode
public class TestModeCfg {
	Mode modeId;
	Hashtable<Phase, PhaseCfg> phaseCfgs;
	
	public TestModeCfg(Mode modeId) {
		this.modeId = modeId;
		 phaseCfgs = new Hashtable<Phase, PhaseCfg>();		
	}
	
	public void addPhaseCfg(PhaseCfg phaseCfg) {
		phaseCfgs.put(phaseCfg.getPhase(), phaseCfg);
	}
	
	public Mode getMode() {
		return modeId;
	}

	public PhaseCfg getPhaseCfg(Phase phase) {
		return phaseCfgs.get(phase);
	}
}
