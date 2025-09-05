package io.openems.edge.meter.ddsu666;

import org.osgi.service.event.EventHandler;

import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.modbusslave.ModbusSlave;
import io.openems.edge.meter.api.ElectricityMeter;

public interface MeterDDSU666 extends OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		
		// Active Power
	    ACTIVE_POWER(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.WATT) //
	            .text("Active Power")),
	    
	    // Voltage
	    VOLTAGE(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.VOLT) //
	            .text("Voltage")),
	    
	    // Current
	    CURRENT(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.AMPERE) //
	            .text("Current")),
	    
	    // Frequency
	    FREQUENCY(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.HERTZ) //
	            .text("Frequency")),
	    
	    // Power Factor
	    POWER_FACTOR(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.NONE) //
	            .text("Power Factor")),
	    
	    // Active Energy
	    ACTIVE_ENERGY(Doc.of(OpenemsType.FLOAT) //
	            .unit(Unit.WATT_HOURS) //
	            .text("Active Energy"));
		private final Doc doc;

		private ChannelId(Doc doc) {
			this.doc = doc;
		}

		@Override
		public Doc doc() {
			return this.doc;
		}
	}

}
