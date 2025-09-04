package io.openems.edge.meter.ddsu666;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.openems.common.exceptions.OpenemsException;
import io.openems.edge.bridge.modbus.api.AbstractOpenemsModbusComponent;
import io.openems.edge.bridge.modbus.api.BridgeModbus;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.bridge.modbus.api.ModbusProtocol;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.meter.api.ElectricityMeter;
import io.openems.common.types.MeterType;



@Designate(ocd = Config.class, factory = true)
@Component(//
		name = "meter.ddsu666", //
		immediate = true, //
		configurationPolicy = ConfigurationPolicy.REQUIRE //
)
public class MeterDDSU666Impl extends AbstractOpenemsModbusComponent implements MeterDDSU666, ElectricityMeter, ModbusComponent, OpenemsComponent {
	
	@Reference
	private ConfigurationAdmin cm;

	@Reference(policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.MANDATORY)
	protected void setModbus(BridgeModbus modbus) {
		super.setModbus(modbus);
	}

	private Config config = null;

	public MeterDDSU666Impl() {
		super(//
				OpenemsComponent.ChannelId.values(), //
				ModbusComponent.ChannelId.values(), //
				MeterDDSU666.ChannelId.values(), //
				ElectricityMeter.ChannelId.values()//
		);
	}


	@Activate
	private void activate(ComponentContext context, Config config) throws OpenemsException {
		if(super.activate(context, config.id(), config.alias(), config.enabled(), config.modbusUnitId(), this.cm, "Modbus",
				config.modbus_id())) {
			return;
		}
		this.config = config;
	}

	@Override
	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	protected ModbusProtocol defineModbusProtocol() {
		// TODO implement ModbusProtocol
		return new ModbusProtocol(this);
	}

	private MeterType meterType = MeterType.PRODUCTION;
	@Override
	public MeterType getMeterType() { 
		return this.config.type();
	}
	@Override
	public String debugLog() {
		return "L:" + this.getActivePower().asString();
	}

}
