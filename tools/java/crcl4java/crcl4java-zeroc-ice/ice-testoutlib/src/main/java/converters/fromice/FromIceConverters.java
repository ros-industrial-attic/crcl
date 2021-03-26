package converters.fromice;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import testinlib.TestClass;
import java2slice.testinlib.TestClassIce;
import crcl.base.DataThingType;
import java2slice.crcl.base.DataThingTypeIce;
import crcl.base.CRCLProgramType;
import java2slice.crcl.base.CRCLProgramTypeIce;
import crcl.base.RotSpeedType;
import java2slice.crcl.base.RotSpeedTypeIce;
import crcl.base.RotAccelType;
import java2slice.crcl.base.RotAccelTypeIce;
import crcl.base.CRCLCommandType;
import java2slice.crcl.base.CRCLCommandTypeIce;
import crcl.base.MiddleCommandType;
import java2slice.crcl.base.MiddleCommandTypeIce;
import crcl.base.ActuateJointsType;
import java2slice.crcl.base.ActuateJointsTypeIce;
import crcl.base.PoseType;
import java2slice.crcl.base.PoseTypeIce;
import crcl.base.JointDetailsType;
import java2slice.crcl.base.JointDetailsTypeIce;
import crcl.base.JointSpeedAccelType;
import java2slice.crcl.base.JointSpeedAccelTypeIce;
import crcl.base.TransSpeedType;
import java2slice.crcl.base.TransSpeedTypeIce;
import crcl.base.TransSpeedAbsoluteType;
import java2slice.crcl.base.TransSpeedAbsoluteTypeIce;
import crcl.base.PoseToleranceType;
import java2slice.crcl.base.PoseToleranceTypeIce;
import crcl.base.TransAccelType;
import java2slice.crcl.base.TransAccelTypeIce;
import crcl.base.WrenchType;
import java2slice.crcl.base.WrenchTypeIce;
import crcl.base.GripperStatusType;
import java2slice.crcl.base.GripperStatusTypeIce;
import crcl.base.InitCanonType;
import java2slice.crcl.base.InitCanonTypeIce;
import crcl.base.MessageType;
import java2slice.crcl.base.MessageTypeIce;
import crcl.base.AngleUnitEnumType;
import java2slice.crcl.base.AngleUnitEnumTypeIce;
import crcl.base.SetIntermediatePoseToleranceType;
import java2slice.crcl.base.SetIntermediatePoseToleranceTypeIce;
import crcl.base.TransAccelAbsoluteType;
import java2slice.crcl.base.TransAccelAbsoluteTypeIce;
import crcl.base.StopMotionType;
import java2slice.crcl.base.StopMotionTypeIce;
import crcl.base.VectorType;
import java2slice.crcl.base.VectorTypeIce;
import crcl.base.SetEndEffectorType;
import java2slice.crcl.base.SetEndEffectorTypeIce;
import crcl.base.CloseToolChangerType;
import java2slice.crcl.base.CloseToolChangerTypeIce;
import crcl.base.ForceUnitEnumType;
import java2slice.crcl.base.ForceUnitEnumTypeIce;
import crcl.base.TorqueUnitEnumType;
import java2slice.crcl.base.TorqueUnitEnumTypeIce;
import crcl.base.ConfigureJointReportsType;
import java2slice.crcl.base.ConfigureJointReportsTypeIce;
import crcl.base.CRCLCommandInstanceType;
import java2slice.crcl.base.CRCLCommandInstanceTypeIce;
import crcl.base.SetRotSpeedType;
import java2slice.crcl.base.SetRotSpeedTypeIce;
import crcl.base.ConfigureStatusReportType;
import java2slice.crcl.base.ConfigureStatusReportTypeIce;
import crcl.base.SetTorqueUnitsType;
import java2slice.crcl.base.SetTorqueUnitsTypeIce;
import crcl.base.JointLimitType;
import java2slice.crcl.base.JointLimitTypeIce;
import crcl.base.ConfigureJointReportType;
import java2slice.crcl.base.ConfigureJointReportTypeIce;
import crcl.base.SetEndPoseToleranceType;
import java2slice.crcl.base.SetEndPoseToleranceTypeIce;
import crcl.base.ParameterSettingType;
import java2slice.crcl.base.ParameterSettingTypeIce;
import crcl.base.SetTransSpeedType;
import java2slice.crcl.base.SetTransSpeedTypeIce;
import crcl.base.JointStatusesType;
import java2slice.crcl.base.JointStatusesTypeIce;
import crcl.base.ParallelGripperStatusType;
import java2slice.crcl.base.ParallelGripperStatusTypeIce;
import crcl.base.LengthUnitEnumType;
import java2slice.crcl.base.LengthUnitEnumTypeIce;
import crcl.base.SetRotAccelType;
import java2slice.crcl.base.SetRotAccelTypeIce;
import crcl.base.SetAngleUnitsType;
import java2slice.crcl.base.SetAngleUnitsTypeIce;
import crcl.base.CommandStatusType;
import java2slice.crcl.base.CommandStatusTypeIce;
import crcl.base.PoseStatusType;
import java2slice.crcl.base.PoseStatusTypeIce;
import crcl.base.TransSpeedRelativeType;
import java2slice.crcl.base.TransSpeedRelativeTypeIce;
import crcl.base.SetForceUnitsType;
import java2slice.crcl.base.SetForceUnitsTypeIce;
import crcl.base.JointStatusType;
import java2slice.crcl.base.JointStatusTypeIce;
import crcl.base.VacuumGripperStatusType;
import java2slice.crcl.base.VacuumGripperStatusTypeIce;
import crcl.base.SetEndEffectorParametersType;
import java2slice.crcl.base.SetEndEffectorParametersTypeIce;
import crcl.base.EndCanonType;
import java2slice.crcl.base.EndCanonTypeIce;
import crcl.base.CRCLStatusType;
import java2slice.crcl.base.CRCLStatusTypeIce;
import crcl.base.RotSpeedRelativeType;
import java2slice.crcl.base.RotSpeedRelativeTypeIce;
import crcl.base.RunProgramType;
import java2slice.crcl.base.RunProgramTypeIce;
import crcl.base.RotAccelRelativeType;
import java2slice.crcl.base.RotAccelRelativeTypeIce;
import crcl.base.MoveThroughToType;
import java2slice.crcl.base.MoveThroughToTypeIce;
import crcl.base.GetStatusType;
import java2slice.crcl.base.GetStatusTypeIce;
import crcl.base.TwistType;
import java2slice.crcl.base.TwistTypeIce;
import crcl.base.MoveScrewType;
import java2slice.crcl.base.MoveScrewTypeIce;
import crcl.base.RotAccelAbsoluteType;
import java2slice.crcl.base.RotAccelAbsoluteTypeIce;
import crcl.base.SetMotionCoordinationType;
import java2slice.crcl.base.SetMotionCoordinationTypeIce;
import crcl.base.ActuateJointType;
import java2slice.crcl.base.ActuateJointTypeIce;
import crcl.base.StopConditionEnumType;
import java2slice.crcl.base.StopConditionEnumTypeIce;
import crcl.base.RotSpeedAbsoluteType;
import java2slice.crcl.base.RotSpeedAbsoluteTypeIce;
import crcl.base.ThreeFingerGripperStatusType;
import java2slice.crcl.base.ThreeFingerGripperStatusTypeIce;
import crcl.base.SetRobotParametersType;
import java2slice.crcl.base.SetRobotParametersTypeIce;
import crcl.base.MoveToType;
import java2slice.crcl.base.MoveToTypeIce;
import crcl.base.ObjectFactory;
import java2slice.crcl.base.ObjectFactoryIce;
import crcl.base.PoseAndSetType;
import java2slice.crcl.base.PoseAndSetTypeIce;
import crcl.base.SettingsStatusType;
import java2slice.crcl.base.SettingsStatusTypeIce;
import crcl.base.TransAccelRelativeType;
import java2slice.crcl.base.TransAccelRelativeTypeIce;
import crcl.base.DwellType;
import java2slice.crcl.base.DwellTypeIce;
import crcl.base.SetLengthUnitsType;
import java2slice.crcl.base.SetLengthUnitsTypeIce;
import crcl.base.OpenToolChangerType;
import java2slice.crcl.base.OpenToolChangerTypeIce;
import crcl.base.SetTransAccelType;
import java2slice.crcl.base.SetTransAccelTypeIce;
import crcl.base.JointForceTorqueType;
import java2slice.crcl.base.JointForceTorqueTypeIce;
import crcl.base.PointType;
import java2slice.crcl.base.PointTypeIce;
import crcl.base.CommandStateEnumType;
import java2slice.crcl.base.CommandStateEnumTypeIce;

public class FromIceConverters  {
	public static List<PoseType> fromIce(PoseTypeIce []in) {
		List<PoseType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<JointStatusType> fromIce(JointStatusTypeIce []in) {
		List<JointStatusType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<ParameterSettingType> fromIce(ParameterSettingTypeIce []in) {
		List<ParameterSettingType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<ActuateJointType> fromIce(ActuateJointTypeIce []in) {
		List<ActuateJointType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<ConfigureJointReportType> fromIce(ConfigureJointReportTypeIce []in) {
		List<ConfigureJointReportType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<MiddleCommandType> fromIce(MiddleCommandTypeIce []in) {
		List<MiddleCommandType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}
	public static List<JointLimitType> fromIce(JointLimitTypeIce []in) {
		List<JointLimitType> newList= new ArrayList<>();
		for(int i = 0; i < in.length; i++) {
			newList.add(fromIce(in[i]));
		}
		return  newList;
	}

	public static TestClass fromIce(TestClassIce in) {
		return ((in !=null)?fromIce(in, new TestClass()):null);
	}

	public static TestClass fromIce(TestClassIce in,TestClass out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		if(in.nameIsNull) {
			out.setName(null);
		} else {
			out.setName(in.name);
		}
		return out;
	}

	public static DataThingType fromIce(DataThingTypeIce in) {
		if(in instanceof PointTypeIce) {
			return fromIce((PointTypeIce)in,new PointType());
		}
		else if(in instanceof JointForceTorqueTypeIce) {
			return fromIce((JointForceTorqueTypeIce)in,new JointForceTorqueType());
		}
		else if(in instanceof SetTransAccelTypeIce) {
			return fromIce((SetTransAccelTypeIce)in,new SetTransAccelType());
		}
		else if(in instanceof OpenToolChangerTypeIce) {
			return fromIce((OpenToolChangerTypeIce)in,new OpenToolChangerType());
		}
		else if(in instanceof SetLengthUnitsTypeIce) {
			return fromIce((SetLengthUnitsTypeIce)in,new SetLengthUnitsType());
		}
		else if(in instanceof DwellTypeIce) {
			return fromIce((DwellTypeIce)in,new DwellType());
		}
		else if(in instanceof TransAccelRelativeTypeIce) {
			return fromIce((TransAccelRelativeTypeIce)in,new TransAccelRelativeType());
		}
		else if(in instanceof SettingsStatusTypeIce) {
			return fromIce((SettingsStatusTypeIce)in,new SettingsStatusType());
		}
		else if(in instanceof PoseAndSetTypeIce) {
			return fromIce((PoseAndSetTypeIce)in,new PoseAndSetType());
		}
		else if(in instanceof MoveToTypeIce) {
			return fromIce((MoveToTypeIce)in,new MoveToType());
		}
		else if(in instanceof SetRobotParametersTypeIce) {
			return fromIce((SetRobotParametersTypeIce)in,new SetRobotParametersType());
		}
		else if(in instanceof ThreeFingerGripperStatusTypeIce) {
			return fromIce((ThreeFingerGripperStatusTypeIce)in,new ThreeFingerGripperStatusType());
		}
		else if(in instanceof RotSpeedAbsoluteTypeIce) {
			return fromIce((RotSpeedAbsoluteTypeIce)in,new RotSpeedAbsoluteType());
		}
		else if(in instanceof ActuateJointTypeIce) {
			return fromIce((ActuateJointTypeIce)in,new ActuateJointType());
		}
		else if(in instanceof SetMotionCoordinationTypeIce) {
			return fromIce((SetMotionCoordinationTypeIce)in,new SetMotionCoordinationType());
		}
		else if(in instanceof RotAccelAbsoluteTypeIce) {
			return fromIce((RotAccelAbsoluteTypeIce)in,new RotAccelAbsoluteType());
		}
		else if(in instanceof MoveScrewTypeIce) {
			return fromIce((MoveScrewTypeIce)in,new MoveScrewType());
		}
		else if(in instanceof TwistTypeIce) {
			return fromIce((TwistTypeIce)in,new TwistType());
		}
		else if(in instanceof GetStatusTypeIce) {
			return fromIce((GetStatusTypeIce)in,new GetStatusType());
		}
		else if(in instanceof MoveThroughToTypeIce) {
			return fromIce((MoveThroughToTypeIce)in,new MoveThroughToType());
		}
		else if(in instanceof RotAccelRelativeTypeIce) {
			return fromIce((RotAccelRelativeTypeIce)in,new RotAccelRelativeType());
		}
		else if(in instanceof RunProgramTypeIce) {
			return fromIce((RunProgramTypeIce)in,new RunProgramType());
		}
		else if(in instanceof RotSpeedRelativeTypeIce) {
			return fromIce((RotSpeedRelativeTypeIce)in,new RotSpeedRelativeType());
		}
		else if(in instanceof CRCLStatusTypeIce) {
			return fromIce((CRCLStatusTypeIce)in,new CRCLStatusType());
		}
		else if(in instanceof EndCanonTypeIce) {
			return fromIce((EndCanonTypeIce)in,new EndCanonType());
		}
		else if(in instanceof SetEndEffectorParametersTypeIce) {
			return fromIce((SetEndEffectorParametersTypeIce)in,new SetEndEffectorParametersType());
		}
		else if(in instanceof VacuumGripperStatusTypeIce) {
			return fromIce((VacuumGripperStatusTypeIce)in,new VacuumGripperStatusType());
		}
		else if(in instanceof JointStatusTypeIce) {
			return fromIce((JointStatusTypeIce)in,new JointStatusType());
		}
		else if(in instanceof SetForceUnitsTypeIce) {
			return fromIce((SetForceUnitsTypeIce)in,new SetForceUnitsType());
		}
		else if(in instanceof TransSpeedRelativeTypeIce) {
			return fromIce((TransSpeedRelativeTypeIce)in,new TransSpeedRelativeType());
		}
		else if(in instanceof PoseStatusTypeIce) {
			return fromIce((PoseStatusTypeIce)in,new PoseStatusType());
		}
		else if(in instanceof CommandStatusTypeIce) {
			return fromIce((CommandStatusTypeIce)in,new CommandStatusType());
		}
		else if(in instanceof SetAngleUnitsTypeIce) {
			return fromIce((SetAngleUnitsTypeIce)in,new SetAngleUnitsType());
		}
		else if(in instanceof SetRotAccelTypeIce) {
			return fromIce((SetRotAccelTypeIce)in,new SetRotAccelType());
		}
		else if(in instanceof ParallelGripperStatusTypeIce) {
			return fromIce((ParallelGripperStatusTypeIce)in,new ParallelGripperStatusType());
		}
		else if(in instanceof JointStatusesTypeIce) {
			return fromIce((JointStatusesTypeIce)in,new JointStatusesType());
		}
		else if(in instanceof SetTransSpeedTypeIce) {
			return fromIce((SetTransSpeedTypeIce)in,new SetTransSpeedType());
		}
		else if(in instanceof ParameterSettingTypeIce) {
			return fromIce((ParameterSettingTypeIce)in,new ParameterSettingType());
		}
		else if(in instanceof SetEndPoseToleranceTypeIce) {
			return fromIce((SetEndPoseToleranceTypeIce)in,new SetEndPoseToleranceType());
		}
		else if(in instanceof ConfigureJointReportTypeIce) {
			return fromIce((ConfigureJointReportTypeIce)in,new ConfigureJointReportType());
		}
		else if(in instanceof JointLimitTypeIce) {
			return fromIce((JointLimitTypeIce)in,new JointLimitType());
		}
		else if(in instanceof SetTorqueUnitsTypeIce) {
			return fromIce((SetTorqueUnitsTypeIce)in,new SetTorqueUnitsType());
		}
		else if(in instanceof ConfigureStatusReportTypeIce) {
			return fromIce((ConfigureStatusReportTypeIce)in,new ConfigureStatusReportType());
		}
		else if(in instanceof SetRotSpeedTypeIce) {
			return fromIce((SetRotSpeedTypeIce)in,new SetRotSpeedType());
		}
		else if(in instanceof CRCLCommandInstanceTypeIce) {
			return fromIce((CRCLCommandInstanceTypeIce)in,new CRCLCommandInstanceType());
		}
		else if(in instanceof ConfigureJointReportsTypeIce) {
			return fromIce((ConfigureJointReportsTypeIce)in,new ConfigureJointReportsType());
		}
		else if(in instanceof CloseToolChangerTypeIce) {
			return fromIce((CloseToolChangerTypeIce)in,new CloseToolChangerType());
		}
		else if(in instanceof SetEndEffectorTypeIce) {
			return fromIce((SetEndEffectorTypeIce)in,new SetEndEffectorType());
		}
		else if(in instanceof VectorTypeIce) {
			return fromIce((VectorTypeIce)in,new VectorType());
		}
		else if(in instanceof StopMotionTypeIce) {
			return fromIce((StopMotionTypeIce)in,new StopMotionType());
		}
		else if(in instanceof TransAccelAbsoluteTypeIce) {
			return fromIce((TransAccelAbsoluteTypeIce)in,new TransAccelAbsoluteType());
		}
		else if(in instanceof SetIntermediatePoseToleranceTypeIce) {
			return fromIce((SetIntermediatePoseToleranceTypeIce)in,new SetIntermediatePoseToleranceType());
		}
		else if(in instanceof MessageTypeIce) {
			return fromIce((MessageTypeIce)in,new MessageType());
		}
		else if(in instanceof InitCanonTypeIce) {
			return fromIce((InitCanonTypeIce)in,new InitCanonType());
		}
		else if(in instanceof WrenchTypeIce) {
			return fromIce((WrenchTypeIce)in,new WrenchType());
		}
		else if(in instanceof PoseToleranceTypeIce) {
			return fromIce((PoseToleranceTypeIce)in,new PoseToleranceType());
		}
		else if(in instanceof TransSpeedAbsoluteTypeIce) {
			return fromIce((TransSpeedAbsoluteTypeIce)in,new TransSpeedAbsoluteType());
		}
		else if(in instanceof JointSpeedAccelTypeIce) {
			return fromIce((JointSpeedAccelTypeIce)in,new JointSpeedAccelType());
		}
		else if(in instanceof PoseTypeIce) {
			return fromIce((PoseTypeIce)in,new PoseType());
		}
		else if(in instanceof ActuateJointsTypeIce) {
			return fromIce((ActuateJointsTypeIce)in,new ActuateJointsType());
		}
		else if(in instanceof CRCLProgramTypeIce) {
			return fromIce((CRCLProgramTypeIce)in,new CRCLProgramType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static DataThingType fromIce(DataThingTypeIce in,DataThingType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		if(in.nameIsNull) {
			out.setName(null);
		} else {
			out.setName(in.name);
		}
		return out;
	}

	public static CRCLProgramType fromIce(CRCLProgramTypeIce in) {
		return ((in !=null)?fromIce(in, new CRCLProgramType()):null);
	}

	public static CRCLProgramType fromIce(CRCLProgramTypeIce in,CRCLProgramType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CRCLProgramType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.getMiddleCommand().clear();
		out.getMiddleCommand().addAll(fromIce(in.middleCommand));
		out.setInitCanon(fromIce(in.initCanon,out.getInitCanon()));
		out.setEndCanon(fromIce(in.endCanon,out.getEndCanon()));
		return out;
	}

	public static RotSpeedType fromIce(RotSpeedTypeIce in) {
		if(in instanceof RotSpeedAbsoluteTypeIce) {
			return fromIce((RotSpeedAbsoluteTypeIce)in,new RotSpeedAbsoluteType());
		}
		else if(in instanceof RotSpeedRelativeTypeIce) {
			return fromIce((RotSpeedRelativeTypeIce)in,new RotSpeedRelativeType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static RotSpeedType fromIce(RotSpeedTypeIce in,RotSpeedType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotSpeedType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		return out;
	}

	public static RotAccelType fromIce(RotAccelTypeIce in) {
		if(in instanceof RotAccelAbsoluteTypeIce) {
			return fromIce((RotAccelAbsoluteTypeIce)in,new RotAccelAbsoluteType());
		}
		else if(in instanceof RotAccelRelativeTypeIce) {
			return fromIce((RotAccelRelativeTypeIce)in,new RotAccelRelativeType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static RotAccelType fromIce(RotAccelTypeIce in,RotAccelType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotAccelType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		return out;
	}

	public static CRCLCommandType fromIce(CRCLCommandTypeIce in) {
		if(in instanceof SetTransAccelTypeIce) {
			return fromIce((SetTransAccelTypeIce)in,new SetTransAccelType());
		}
		else if(in instanceof OpenToolChangerTypeIce) {
			return fromIce((OpenToolChangerTypeIce)in,new OpenToolChangerType());
		}
		else if(in instanceof SetLengthUnitsTypeIce) {
			return fromIce((SetLengthUnitsTypeIce)in,new SetLengthUnitsType());
		}
		else if(in instanceof DwellTypeIce) {
			return fromIce((DwellTypeIce)in,new DwellType());
		}
		else if(in instanceof MoveToTypeIce) {
			return fromIce((MoveToTypeIce)in,new MoveToType());
		}
		else if(in instanceof SetRobotParametersTypeIce) {
			return fromIce((SetRobotParametersTypeIce)in,new SetRobotParametersType());
		}
		else if(in instanceof SetMotionCoordinationTypeIce) {
			return fromIce((SetMotionCoordinationTypeIce)in,new SetMotionCoordinationType());
		}
		else if(in instanceof MoveScrewTypeIce) {
			return fromIce((MoveScrewTypeIce)in,new MoveScrewType());
		}
		else if(in instanceof GetStatusTypeIce) {
			return fromIce((GetStatusTypeIce)in,new GetStatusType());
		}
		else if(in instanceof MoveThroughToTypeIce) {
			return fromIce((MoveThroughToTypeIce)in,new MoveThroughToType());
		}
		else if(in instanceof RunProgramTypeIce) {
			return fromIce((RunProgramTypeIce)in,new RunProgramType());
		}
		else if(in instanceof EndCanonTypeIce) {
			return fromIce((EndCanonTypeIce)in,new EndCanonType());
		}
		else if(in instanceof SetEndEffectorParametersTypeIce) {
			return fromIce((SetEndEffectorParametersTypeIce)in,new SetEndEffectorParametersType());
		}
		else if(in instanceof SetForceUnitsTypeIce) {
			return fromIce((SetForceUnitsTypeIce)in,new SetForceUnitsType());
		}
		else if(in instanceof SetAngleUnitsTypeIce) {
			return fromIce((SetAngleUnitsTypeIce)in,new SetAngleUnitsType());
		}
		else if(in instanceof SetRotAccelTypeIce) {
			return fromIce((SetRotAccelTypeIce)in,new SetRotAccelType());
		}
		else if(in instanceof SetTransSpeedTypeIce) {
			return fromIce((SetTransSpeedTypeIce)in,new SetTransSpeedType());
		}
		else if(in instanceof SetEndPoseToleranceTypeIce) {
			return fromIce((SetEndPoseToleranceTypeIce)in,new SetEndPoseToleranceType());
		}
		else if(in instanceof SetTorqueUnitsTypeIce) {
			return fromIce((SetTorqueUnitsTypeIce)in,new SetTorqueUnitsType());
		}
		else if(in instanceof ConfigureStatusReportTypeIce) {
			return fromIce((ConfigureStatusReportTypeIce)in,new ConfigureStatusReportType());
		}
		else if(in instanceof SetRotSpeedTypeIce) {
			return fromIce((SetRotSpeedTypeIce)in,new SetRotSpeedType());
		}
		else if(in instanceof ConfigureJointReportsTypeIce) {
			return fromIce((ConfigureJointReportsTypeIce)in,new ConfigureJointReportsType());
		}
		else if(in instanceof CloseToolChangerTypeIce) {
			return fromIce((CloseToolChangerTypeIce)in,new CloseToolChangerType());
		}
		else if(in instanceof SetEndEffectorTypeIce) {
			return fromIce((SetEndEffectorTypeIce)in,new SetEndEffectorType());
		}
		else if(in instanceof StopMotionTypeIce) {
			return fromIce((StopMotionTypeIce)in,new StopMotionType());
		}
		else if(in instanceof SetIntermediatePoseToleranceTypeIce) {
			return fromIce((SetIntermediatePoseToleranceTypeIce)in,new SetIntermediatePoseToleranceType());
		}
		else if(in instanceof MessageTypeIce) {
			return fromIce((MessageTypeIce)in,new MessageType());
		}
		else if(in instanceof InitCanonTypeIce) {
			return fromIce((InitCanonTypeIce)in,new InitCanonType());
		}
		else if(in instanceof ActuateJointsTypeIce) {
			return fromIce((ActuateJointsTypeIce)in,new ActuateJointsType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static CRCLCommandType fromIce(CRCLCommandTypeIce in,CRCLCommandType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CRCLCommandType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.commandIDIsNull) {
			out.setCommandID(null);
		} else {
			out.setCommandID(BigInteger.valueOf(in.commandID));
		}
		return out;
	}

	public static MiddleCommandType fromIce(MiddleCommandTypeIce in) {
		if(in instanceof SetTransAccelTypeIce) {
			return fromIce((SetTransAccelTypeIce)in,new SetTransAccelType());
		}
		else if(in instanceof OpenToolChangerTypeIce) {
			return fromIce((OpenToolChangerTypeIce)in,new OpenToolChangerType());
		}
		else if(in instanceof SetLengthUnitsTypeIce) {
			return fromIce((SetLengthUnitsTypeIce)in,new SetLengthUnitsType());
		}
		else if(in instanceof DwellTypeIce) {
			return fromIce((DwellTypeIce)in,new DwellType());
		}
		else if(in instanceof MoveToTypeIce) {
			return fromIce((MoveToTypeIce)in,new MoveToType());
		}
		else if(in instanceof SetRobotParametersTypeIce) {
			return fromIce((SetRobotParametersTypeIce)in,new SetRobotParametersType());
		}
		else if(in instanceof SetMotionCoordinationTypeIce) {
			return fromIce((SetMotionCoordinationTypeIce)in,new SetMotionCoordinationType());
		}
		else if(in instanceof MoveScrewTypeIce) {
			return fromIce((MoveScrewTypeIce)in,new MoveScrewType());
		}
		else if(in instanceof GetStatusTypeIce) {
			return fromIce((GetStatusTypeIce)in,new GetStatusType());
		}
		else if(in instanceof MoveThroughToTypeIce) {
			return fromIce((MoveThroughToTypeIce)in,new MoveThroughToType());
		}
		else if(in instanceof RunProgramTypeIce) {
			return fromIce((RunProgramTypeIce)in,new RunProgramType());
		}
		else if(in instanceof SetEndEffectorParametersTypeIce) {
			return fromIce((SetEndEffectorParametersTypeIce)in,new SetEndEffectorParametersType());
		}
		else if(in instanceof SetForceUnitsTypeIce) {
			return fromIce((SetForceUnitsTypeIce)in,new SetForceUnitsType());
		}
		else if(in instanceof SetAngleUnitsTypeIce) {
			return fromIce((SetAngleUnitsTypeIce)in,new SetAngleUnitsType());
		}
		else if(in instanceof SetRotAccelTypeIce) {
			return fromIce((SetRotAccelTypeIce)in,new SetRotAccelType());
		}
		else if(in instanceof SetTransSpeedTypeIce) {
			return fromIce((SetTransSpeedTypeIce)in,new SetTransSpeedType());
		}
		else if(in instanceof SetEndPoseToleranceTypeIce) {
			return fromIce((SetEndPoseToleranceTypeIce)in,new SetEndPoseToleranceType());
		}
		else if(in instanceof SetTorqueUnitsTypeIce) {
			return fromIce((SetTorqueUnitsTypeIce)in,new SetTorqueUnitsType());
		}
		else if(in instanceof ConfigureStatusReportTypeIce) {
			return fromIce((ConfigureStatusReportTypeIce)in,new ConfigureStatusReportType());
		}
		else if(in instanceof SetRotSpeedTypeIce) {
			return fromIce((SetRotSpeedTypeIce)in,new SetRotSpeedType());
		}
		else if(in instanceof ConfigureJointReportsTypeIce) {
			return fromIce((ConfigureJointReportsTypeIce)in,new ConfigureJointReportsType());
		}
		else if(in instanceof CloseToolChangerTypeIce) {
			return fromIce((CloseToolChangerTypeIce)in,new CloseToolChangerType());
		}
		else if(in instanceof SetEndEffectorTypeIce) {
			return fromIce((SetEndEffectorTypeIce)in,new SetEndEffectorType());
		}
		else if(in instanceof StopMotionTypeIce) {
			return fromIce((StopMotionTypeIce)in,new StopMotionType());
		}
		else if(in instanceof SetIntermediatePoseToleranceTypeIce) {
			return fromIce((SetIntermediatePoseToleranceTypeIce)in,new SetIntermediatePoseToleranceType());
		}
		else if(in instanceof MessageTypeIce) {
			return fromIce((MessageTypeIce)in,new MessageType());
		}
		else if(in instanceof ActuateJointsTypeIce) {
			return fromIce((ActuateJointsTypeIce)in,new ActuateJointsType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static MiddleCommandType fromIce(MiddleCommandTypeIce in,MiddleCommandType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (MiddleCommandType) fromIce((CRCLCommandTypeIce)in,(CRCLCommandType)out);
		return out;
	}

	public static ActuateJointsType fromIce(ActuateJointsTypeIce in) {
		return ((in !=null)?fromIce(in, new ActuateJointsType()):null);
	}

	public static ActuateJointsType fromIce(ActuateJointsTypeIce in,ActuateJointsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ActuateJointsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.getActuateJoint().clear();
		out.getActuateJoint().addAll(fromIce(in.actuateJoint));
		return out;
	}

	public static PoseType fromIce(PoseTypeIce in) {
		if(in instanceof PoseAndSetTypeIce) {
			return fromIce((PoseAndSetTypeIce)in,new PoseAndSetType());
		}
		else if(in instanceof PoseTypeIce) {
			return fromIce((PoseTypeIce)in,new PoseType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static PoseType fromIce(PoseTypeIce in,PoseType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (PoseType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setPoint(fromIce(in.point,out.getPoint()));
		out.setZAxis(fromIce(in.zAxis,out.getZAxis()));
		out.setXAxis(fromIce(in.xAxis,out.getXAxis()));
		return out;
	}

	public static JointDetailsType fromIce(JointDetailsTypeIce in) {
		if(in instanceof JointForceTorqueTypeIce) {
			return fromIce((JointForceTorqueTypeIce)in,new JointForceTorqueType());
		}
		else if(in instanceof JointSpeedAccelTypeIce) {
			return fromIce((JointSpeedAccelTypeIce)in,new JointSpeedAccelType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static JointDetailsType fromIce(JointDetailsTypeIce in,JointDetailsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointDetailsType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		return out;
	}

	public static JointSpeedAccelType fromIce(JointSpeedAccelTypeIce in) {
		return ((in !=null)?fromIce(in, new JointSpeedAccelType()):null);
	}

	public static JointSpeedAccelType fromIce(JointSpeedAccelTypeIce in,JointSpeedAccelType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointSpeedAccelType) fromIce((JointDetailsTypeIce)in,(JointDetailsType)out);
		if(in.jointAccelIsNull) {
			out.setJointAccel(null);
		} else {
			out.setJointAccel(BigDecimal.valueOf(in.jointAccel));
		}
		if(in.jointSpeedIsNull) {
			out.setJointSpeed(null);
		} else {
			out.setJointSpeed(BigDecimal.valueOf(in.jointSpeed));
		}
		return out;
	}

	public static TransSpeedType fromIce(TransSpeedTypeIce in) {
		if(in instanceof TransSpeedRelativeTypeIce) {
			return fromIce((TransSpeedRelativeTypeIce)in,new TransSpeedRelativeType());
		}
		else if(in instanceof TransSpeedAbsoluteTypeIce) {
			return fromIce((TransSpeedAbsoluteTypeIce)in,new TransSpeedAbsoluteType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static TransSpeedType fromIce(TransSpeedTypeIce in,TransSpeedType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransSpeedType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		return out;
	}

	public static TransSpeedAbsoluteType fromIce(TransSpeedAbsoluteTypeIce in) {
		return ((in !=null)?fromIce(in, new TransSpeedAbsoluteType()):null);
	}

	public static TransSpeedAbsoluteType fromIce(TransSpeedAbsoluteTypeIce in,TransSpeedAbsoluteType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransSpeedAbsoluteType) fromIce((TransSpeedTypeIce)in,(TransSpeedType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		return out;
	}

	public static PoseToleranceType fromIce(PoseToleranceTypeIce in) {
		return ((in !=null)?fromIce(in, new PoseToleranceType()):null);
	}

	public static PoseToleranceType fromIce(PoseToleranceTypeIce in,PoseToleranceType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (PoseToleranceType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.yPointToleranceIsNull) {
			out.setYPointTolerance(null);
		} else {
			out.setYPointTolerance(BigDecimal.valueOf(in.yPointTolerance));
		}
		if(in.xAxisToleranceIsNull) {
			out.setXAxisTolerance(null);
		} else {
			out.setXAxisTolerance(BigDecimal.valueOf(in.xAxisTolerance));
		}
		if(in.zPointToleranceIsNull) {
			out.setZPointTolerance(null);
		} else {
			out.setZPointTolerance(BigDecimal.valueOf(in.zPointTolerance));
		}
		if(in.xPointToleranceIsNull) {
			out.setXPointTolerance(null);
		} else {
			out.setXPointTolerance(BigDecimal.valueOf(in.xPointTolerance));
		}
		if(in.zAxisToleranceIsNull) {
			out.setZAxisTolerance(null);
		} else {
			out.setZAxisTolerance(BigDecimal.valueOf(in.zAxisTolerance));
		}
		return out;
	}

	public static TransAccelType fromIce(TransAccelTypeIce in) {
		if(in instanceof TransAccelRelativeTypeIce) {
			return fromIce((TransAccelRelativeTypeIce)in,new TransAccelRelativeType());
		}
		else if(in instanceof TransAccelAbsoluteTypeIce) {
			return fromIce((TransAccelAbsoluteTypeIce)in,new TransAccelAbsoluteType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static TransAccelType fromIce(TransAccelTypeIce in,TransAccelType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransAccelType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		return out;
	}

	public static WrenchType fromIce(WrenchTypeIce in) {
		return ((in !=null)?fromIce(in, new WrenchType()):null);
	}

	public static WrenchType fromIce(WrenchTypeIce in,WrenchType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (WrenchType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setMoment(fromIce(in.moment,out.getMoment()));
		out.setForce(fromIce(in.force,out.getForce()));
		return out;
	}

	public static GripperStatusType fromIce(GripperStatusTypeIce in) {
		if(in instanceof ThreeFingerGripperStatusTypeIce) {
			return fromIce((ThreeFingerGripperStatusTypeIce)in,new ThreeFingerGripperStatusType());
		}
		else if(in instanceof VacuumGripperStatusTypeIce) {
			return fromIce((VacuumGripperStatusTypeIce)in,new VacuumGripperStatusType());
		}
		else if(in instanceof ParallelGripperStatusTypeIce) {
			return fromIce((ParallelGripperStatusTypeIce)in,new ParallelGripperStatusType());
		}
		throw new IllegalArgumentException(in+" is not of recognized type");
	}

	public static GripperStatusType fromIce(GripperStatusTypeIce in,GripperStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (GripperStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.holdingObjectIsNull) {
			out.setHoldingObject(null);
		} else {
			out.setHoldingObject(in.holdingObject);
		}
		if(in.gripperNameIsNull) {
			out.setGripperName(null);
		} else {
			out.setGripperName(in.gripperName);
		}
		return out;
	}

	public static InitCanonType fromIce(InitCanonTypeIce in) {
		return ((in !=null)?fromIce(in, new InitCanonType()):null);
	}

	public static InitCanonType fromIce(InitCanonTypeIce in,InitCanonType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (InitCanonType) fromIce((CRCLCommandTypeIce)in,(CRCLCommandType)out);
		return out;
	}

	public static MessageType fromIce(MessageTypeIce in) {
		return ((in !=null)?fromIce(in, new MessageType()):null);
	}

	public static MessageType fromIce(MessageTypeIce in,MessageType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (MessageType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		if(in.messageIsNull) {
			out.setMessage(null);
		} else {
			out.setMessage(in.message);
		}
		return out;
	}
	public static AngleUnitEnumType fromIce(AngleUnitEnumTypeIce in) {
		return AngleUnitEnumType.values()[in.value()];
	}

	public static SetIntermediatePoseToleranceType fromIce(SetIntermediatePoseToleranceTypeIce in) {
		return ((in !=null)?fromIce(in, new SetIntermediatePoseToleranceType()):null);
	}

	public static SetIntermediatePoseToleranceType fromIce(SetIntermediatePoseToleranceTypeIce in,SetIntermediatePoseToleranceType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetIntermediatePoseToleranceType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setTolerance(fromIce(in.tolerance,out.getTolerance()));
		return out;
	}

	public static TransAccelAbsoluteType fromIce(TransAccelAbsoluteTypeIce in) {
		return ((in !=null)?fromIce(in, new TransAccelAbsoluteType()):null);
	}

	public static TransAccelAbsoluteType fromIce(TransAccelAbsoluteTypeIce in,TransAccelAbsoluteType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransAccelAbsoluteType) fromIce((TransAccelTypeIce)in,(TransAccelType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		return out;
	}

	public static StopMotionType fromIce(StopMotionTypeIce in) {
		return ((in !=null)?fromIce(in, new StopMotionType()):null);
	}

	public static StopMotionType fromIce(StopMotionTypeIce in,StopMotionType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (StopMotionType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setStopCondition(fromIce(in.stopCondition));
		return out;
	}

	public static VectorType fromIce(VectorTypeIce in) {
		return ((in !=null)?fromIce(in, new VectorType()):null);
	}

	public static VectorType fromIce(VectorTypeIce in,VectorType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (VectorType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.jIsNull) {
			out.setJ(null);
		} else {
			out.setJ(BigDecimal.valueOf(in.j));
		}
		if(in.kIsNull) {
			out.setK(null);
		} else {
			out.setK(BigDecimal.valueOf(in.k));
		}
		if(in.iIsNull) {
			out.setI(null);
		} else {
			out.setI(BigDecimal.valueOf(in.i));
		}
		return out;
	}

	public static SetEndEffectorType fromIce(SetEndEffectorTypeIce in) {
		return ((in !=null)?fromIce(in, new SetEndEffectorType()):null);
	}

	public static SetEndEffectorType fromIce(SetEndEffectorTypeIce in,SetEndEffectorType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetEndEffectorType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		return out;
	}

	public static CloseToolChangerType fromIce(CloseToolChangerTypeIce in) {
		return ((in !=null)?fromIce(in, new CloseToolChangerType()):null);
	}

	public static CloseToolChangerType fromIce(CloseToolChangerTypeIce in,CloseToolChangerType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CloseToolChangerType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		return out;
	}
	public static ForceUnitEnumType fromIce(ForceUnitEnumTypeIce in) {
		return ForceUnitEnumType.values()[in.value()];
	}
	public static TorqueUnitEnumType fromIce(TorqueUnitEnumTypeIce in) {
		return TorqueUnitEnumType.values()[in.value()];
	}

	public static ConfigureJointReportsType fromIce(ConfigureJointReportsTypeIce in) {
		return ((in !=null)?fromIce(in, new ConfigureJointReportsType()):null);
	}

	public static ConfigureJointReportsType fromIce(ConfigureJointReportsTypeIce in,ConfigureJointReportsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ConfigureJointReportsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setResetAll(in.resetAll);
		out.getConfigureJointReport().clear();
		out.getConfigureJointReport().addAll(fromIce(in.configureJointReport));
		return out;
	}

	public static CRCLCommandInstanceType fromIce(CRCLCommandInstanceTypeIce in) {
		return ((in !=null)?fromIce(in, new CRCLCommandInstanceType()):null);
	}

	public static CRCLCommandInstanceType fromIce(CRCLCommandInstanceTypeIce in,CRCLCommandInstanceType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CRCLCommandInstanceType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setCRCLCommand(fromIce(in.cRCLCommand,out.getCRCLCommand()));
		if(in.programIndexIsNull) {
			out.setProgramIndex(null);
		} else {
			out.setProgramIndex(BigInteger.valueOf(in.programIndex));
		}
		if(in.programLengthIsNull) {
			out.setProgramLength(null);
		} else {
			out.setProgramLength(BigInteger.valueOf(in.programLength));
		}
		if(in.programFileIsNull) {
			out.setProgramFile(null);
		} else {
			out.setProgramFile(in.programFile);
		}
		return out;
	}

	public static SetRotSpeedType fromIce(SetRotSpeedTypeIce in) {
		return ((in !=null)?fromIce(in, new SetRotSpeedType()):null);
	}

	public static SetRotSpeedType fromIce(SetRotSpeedTypeIce in,SetRotSpeedType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetRotSpeedType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setRotSpeed(fromIce(in.rotSpeed,out.getRotSpeed()));
		return out;
	}

	public static ConfigureStatusReportType fromIce(ConfigureStatusReportTypeIce in) {
		return ((in !=null)?fromIce(in, new ConfigureStatusReportType()):null);
	}

	public static ConfigureStatusReportType fromIce(ConfigureStatusReportTypeIce in,ConfigureStatusReportType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ConfigureStatusReportType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setReportPoseStatus(in.reportPoseStatus);
		out.setReportSettingsStatus(in.reportSettingsStatus);
		out.setReportJointStatuses(in.reportJointStatuses);
		out.setReportGripperStatus(in.reportGripperStatus);
		return out;
	}

	public static SetTorqueUnitsType fromIce(SetTorqueUnitsTypeIce in) {
		return ((in !=null)?fromIce(in, new SetTorqueUnitsType()):null);
	}

	public static SetTorqueUnitsType fromIce(SetTorqueUnitsTypeIce in,SetTorqueUnitsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetTorqueUnitsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setUnitName(fromIce(in.unitName));
		return out;
	}

	public static JointLimitType fromIce(JointLimitTypeIce in) {
		return ((in !=null)?fromIce(in, new JointLimitType()):null);
	}

	public static JointLimitType fromIce(JointLimitTypeIce in,JointLimitType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointLimitType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.jointNumberIsNull) {
			out.setJointNumber(null);
		} else {
			out.setJointNumber(BigInteger.valueOf(in.jointNumber));
		}
		if(in.jointMaxTorqueOrForceIsNull) {
			out.setJointMaxTorqueOrForce(null);
		} else {
			out.setJointMaxTorqueOrForce(BigDecimal.valueOf(in.jointMaxTorqueOrForce));
		}
		if(in.jointMaxVelocityIsNull) {
			out.setJointMaxVelocity(null);
		} else {
			out.setJointMaxVelocity(BigDecimal.valueOf(in.jointMaxVelocity));
		}
		if(in.jointMaxPositionIsNull) {
			out.setJointMaxPosition(null);
		} else {
			out.setJointMaxPosition(BigDecimal.valueOf(in.jointMaxPosition));
		}
		if(in.jointMinPositionIsNull) {
			out.setJointMinPosition(null);
		} else {
			out.setJointMinPosition(BigDecimal.valueOf(in.jointMinPosition));
		}
		return out;
	}

	public static ConfigureJointReportType fromIce(ConfigureJointReportTypeIce in) {
		return ((in !=null)?fromIce(in, new ConfigureJointReportType()):null);
	}

	public static ConfigureJointReportType fromIce(ConfigureJointReportTypeIce in,ConfigureJointReportType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ConfigureJointReportType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.jointNumberIsNull) {
			out.setJointNumber(null);
		} else {
			out.setJointNumber(BigInteger.valueOf(in.jointNumber));
		}
		out.setReportPosition(in.reportPosition);
		out.setReportVelocity(in.reportVelocity);
		out.setReportTorqueOrForce(in.reportTorqueOrForce);
		return out;
	}

	public static SetEndPoseToleranceType fromIce(SetEndPoseToleranceTypeIce in) {
		return ((in !=null)?fromIce(in, new SetEndPoseToleranceType()):null);
	}

	public static SetEndPoseToleranceType fromIce(SetEndPoseToleranceTypeIce in,SetEndPoseToleranceType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetEndPoseToleranceType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setTolerance(fromIce(in.tolerance,out.getTolerance()));
		return out;
	}

	public static ParameterSettingType fromIce(ParameterSettingTypeIce in) {
		return ((in !=null)?fromIce(in, new ParameterSettingType()):null);
	}

	public static ParameterSettingType fromIce(ParameterSettingTypeIce in,ParameterSettingType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ParameterSettingType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.parameterValueIsNull) {
			out.setParameterValue(null);
		} else {
			out.setParameterValue(in.parameterValue);
		}
		if(in.parameterNameIsNull) {
			out.setParameterName(null);
		} else {
			out.setParameterName(in.parameterName);
		}
		return out;
	}

	public static SetTransSpeedType fromIce(SetTransSpeedTypeIce in) {
		return ((in !=null)?fromIce(in, new SetTransSpeedType()):null);
	}

	public static SetTransSpeedType fromIce(SetTransSpeedTypeIce in,SetTransSpeedType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetTransSpeedType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setTransSpeed(fromIce(in.transSpeed,out.getTransSpeed()));
		return out;
	}

	public static JointStatusesType fromIce(JointStatusesTypeIce in) {
		return ((in !=null)?fromIce(in, new JointStatusesType()):null);
	}

	public static JointStatusesType fromIce(JointStatusesTypeIce in,JointStatusesType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointStatusesType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.getJointStatus().clear();
		out.getJointStatus().addAll(fromIce(in.jointStatus));
		return out;
	}

	public static ParallelGripperStatusType fromIce(ParallelGripperStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new ParallelGripperStatusType()):null);
	}

	public static ParallelGripperStatusType fromIce(ParallelGripperStatusTypeIce in,ParallelGripperStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ParallelGripperStatusType) fromIce((GripperStatusTypeIce)in,(GripperStatusType)out);
		if(in.separationIsNull) {
			out.setSeparation(null);
		} else {
			out.setSeparation(BigDecimal.valueOf(in.separation));
		}
		return out;
	}
	public static LengthUnitEnumType fromIce(LengthUnitEnumTypeIce in) {
		return LengthUnitEnumType.values()[in.value()];
	}

	public static SetRotAccelType fromIce(SetRotAccelTypeIce in) {
		return ((in !=null)?fromIce(in, new SetRotAccelType()):null);
	}

	public static SetRotAccelType fromIce(SetRotAccelTypeIce in,SetRotAccelType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetRotAccelType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setRotAccel(fromIce(in.rotAccel,out.getRotAccel()));
		return out;
	}

	public static SetAngleUnitsType fromIce(SetAngleUnitsTypeIce in) {
		return ((in !=null)?fromIce(in, new SetAngleUnitsType()):null);
	}

	public static SetAngleUnitsType fromIce(SetAngleUnitsTypeIce in,SetAngleUnitsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetAngleUnitsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setUnitName(fromIce(in.unitName));
		return out;
	}

	public static CommandStatusType fromIce(CommandStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new CommandStatusType()):null);
	}

	public static CommandStatusType fromIce(CommandStatusTypeIce in,CommandStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CommandStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.programIndexIsNull) {
			out.setProgramIndex(null);
		} else {
			out.setProgramIndex(BigInteger.valueOf(in.programIndex));
		}
		if(in.statusIDIsNull) {
			out.setStatusID(null);
		} else {
			out.setStatusID(BigInteger.valueOf(in.statusID));
		}
		if(in.stateDescriptionIsNull) {
			out.setStateDescription(null);
		} else {
			out.setStateDescription(in.stateDescription);
		}
		if(in.commandIDIsNull) {
			out.setCommandID(null);
		} else {
			out.setCommandID(BigInteger.valueOf(in.commandID));
		}
		if(in.programLengthIsNull) {
			out.setProgramLength(null);
		} else {
			out.setProgramLength(BigInteger.valueOf(in.programLength));
		}
		out.setCommandState(fromIce(in.commandState));
		if(in.programFileIsNull) {
			out.setProgramFile(null);
		} else {
			out.setProgramFile(in.programFile);
		}
		return out;
	}

	public static PoseStatusType fromIce(PoseStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new PoseStatusType()):null);
	}

	public static PoseStatusType fromIce(PoseStatusTypeIce in,PoseStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (PoseStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setPose(fromIce(in.pose,out.getPose()));
		out.setTwist(fromIce(in.twist,out.getTwist()));
		if(in.configurationIsNull) {
			out.setConfiguration(null);
		} else {
			out.setConfiguration(in.configuration);
		}
		out.setWrench(fromIce(in.wrench,out.getWrench()));
		return out;
	}

	public static TransSpeedRelativeType fromIce(TransSpeedRelativeTypeIce in) {
		return ((in !=null)?fromIce(in, new TransSpeedRelativeType()):null);
	}

	public static TransSpeedRelativeType fromIce(TransSpeedRelativeTypeIce in,TransSpeedRelativeType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransSpeedRelativeType) fromIce((TransSpeedTypeIce)in,(TransSpeedType)out);
		if(in.fractionIsNull) {
			out.setFraction(null);
		} else {
			out.setFraction(BigDecimal.valueOf(in.fraction));
		}
		return out;
	}

	public static SetForceUnitsType fromIce(SetForceUnitsTypeIce in) {
		return ((in !=null)?fromIce(in, new SetForceUnitsType()):null);
	}

	public static SetForceUnitsType fromIce(SetForceUnitsTypeIce in,SetForceUnitsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetForceUnitsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setUnitName(fromIce(in.unitName));
		return out;
	}

	public static JointStatusType fromIce(JointStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new JointStatusType()):null);
	}

	public static JointStatusType fromIce(JointStatusTypeIce in,JointStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.jointPositionIsNull) {
			out.setJointPosition(null);
		} else {
			out.setJointPosition(BigDecimal.valueOf(in.jointPosition));
		}
		if(in.jointVelocityIsNull) {
			out.setJointVelocity(null);
		} else {
			out.setJointVelocity(BigDecimal.valueOf(in.jointVelocity));
		}
		if(in.jointNumberIsNull) {
			out.setJointNumber(null);
		} else {
			out.setJointNumber(BigInteger.valueOf(in.jointNumber));
		}
		if(in.jointTorqueOrForceIsNull) {
			out.setJointTorqueOrForce(null);
		} else {
			out.setJointTorqueOrForce(BigDecimal.valueOf(in.jointTorqueOrForce));
		}
		return out;
	}

	public static VacuumGripperStatusType fromIce(VacuumGripperStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new VacuumGripperStatusType()):null);
	}

	public static VacuumGripperStatusType fromIce(VacuumGripperStatusTypeIce in,VacuumGripperStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (VacuumGripperStatusType) fromIce((GripperStatusTypeIce)in,(GripperStatusType)out);
		out.setIsPowered(in.isPowered);
		return out;
	}

	public static SetEndEffectorParametersType fromIce(SetEndEffectorParametersTypeIce in) {
		return ((in !=null)?fromIce(in, new SetEndEffectorParametersType()):null);
	}

	public static SetEndEffectorParametersType fromIce(SetEndEffectorParametersTypeIce in,SetEndEffectorParametersType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetEndEffectorParametersType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.getParameterSetting().clear();
		out.getParameterSetting().addAll(fromIce(in.parameterSetting));
		return out;
	}

	public static EndCanonType fromIce(EndCanonTypeIce in) {
		return ((in !=null)?fromIce(in, new EndCanonType()):null);
	}

	public static EndCanonType fromIce(EndCanonTypeIce in,EndCanonType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (EndCanonType) fromIce((CRCLCommandTypeIce)in,(CRCLCommandType)out);
		return out;
	}

	public static CRCLStatusType fromIce(CRCLStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new CRCLStatusType()):null);
	}

	public static CRCLStatusType fromIce(CRCLStatusTypeIce in,CRCLStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (CRCLStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setGripperStatus(fromIce(in.gripperStatus,out.getGripperStatus()));
		out.setJointStatuses(fromIce(in.jointStatuses,out.getJointStatuses()));
		out.setPoseStatus(fromIce(in.poseStatus,out.getPoseStatus()));
		out.setSettingsStatus(fromIce(in.settingsStatus,out.getSettingsStatus()));
		out.setCommandStatus(fromIce(in.commandStatus,out.getCommandStatus()));
		return out;
	}

	public static RotSpeedRelativeType fromIce(RotSpeedRelativeTypeIce in) {
		return ((in !=null)?fromIce(in, new RotSpeedRelativeType()):null);
	}

	public static RotSpeedRelativeType fromIce(RotSpeedRelativeTypeIce in,RotSpeedRelativeType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotSpeedRelativeType) fromIce((RotSpeedTypeIce)in,(RotSpeedType)out);
		if(in.fractionIsNull) {
			out.setFraction(null);
		} else {
			out.setFraction(BigDecimal.valueOf(in.fraction));
		}
		return out;
	}

	public static RunProgramType fromIce(RunProgramTypeIce in) {
		return ((in !=null)?fromIce(in, new RunProgramType()):null);
	}

	public static RunProgramType fromIce(RunProgramTypeIce in,RunProgramType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RunProgramType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		if(in.programTextIsNull) {
			out.setProgramText(null);
		} else {
			out.setProgramText(in.programText);
		}
		return out;
	}

	public static RotAccelRelativeType fromIce(RotAccelRelativeTypeIce in) {
		return ((in !=null)?fromIce(in, new RotAccelRelativeType()):null);
	}

	public static RotAccelRelativeType fromIce(RotAccelRelativeTypeIce in,RotAccelRelativeType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotAccelRelativeType) fromIce((RotAccelTypeIce)in,(RotAccelType)out);
		if(in.fractionIsNull) {
			out.setFraction(null);
		} else {
			out.setFraction(BigDecimal.valueOf(in.fraction));
		}
		return out;
	}

	public static MoveThroughToType fromIce(MoveThroughToTypeIce in) {
		return ((in !=null)?fromIce(in, new MoveThroughToType()):null);
	}

	public static MoveThroughToType fromIce(MoveThroughToTypeIce in,MoveThroughToType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (MoveThroughToType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setMoveStraight(in.moveStraight);
		out.getWaypoint().clear();
		out.getWaypoint().addAll(fromIce(in.waypoint));
		if(in.numPositionsIsNull) {
			out.setNumPositions(null);
		} else {
			out.setNumPositions(BigInteger.valueOf(in.numPositions));
		}
		return out;
	}

	public static GetStatusType fromIce(GetStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new GetStatusType()):null);
	}

	public static GetStatusType fromIce(GetStatusTypeIce in,GetStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (GetStatusType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		return out;
	}

	public static TwistType fromIce(TwistTypeIce in) {
		return ((in !=null)?fromIce(in, new TwistType()):null);
	}

	public static TwistType fromIce(TwistTypeIce in,TwistType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TwistType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setLinearVelocity(fromIce(in.linearVelocity,out.getLinearVelocity()));
		out.setAngularVelocity(fromIce(in.angularVelocity,out.getAngularVelocity()));
		return out;
	}

	public static MoveScrewType fromIce(MoveScrewTypeIce in) {
		return ((in !=null)?fromIce(in, new MoveScrewType()):null);
	}

	public static MoveScrewType fromIce(MoveScrewTypeIce in,MoveScrewType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (MoveScrewType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		if(in.turnIsNull) {
			out.setTurn(null);
		} else {
			out.setTurn(BigDecimal.valueOf(in.turn));
		}
		if(in.axialDistanceFreeIsNull) {
			out.setAxialDistanceFree(null);
		} else {
			out.setAxialDistanceFree(BigDecimal.valueOf(in.axialDistanceFree));
		}
		if(in.axialDistanceScrewIsNull) {
			out.setAxialDistanceScrew(null);
		} else {
			out.setAxialDistanceScrew(BigDecimal.valueOf(in.axialDistanceScrew));
		}
		out.setAxisPoint(fromIce(in.axisPoint,out.getAxisPoint()));
		out.setStartPosition(fromIce(in.startPosition,out.getStartPosition()));
		return out;
	}

	public static RotAccelAbsoluteType fromIce(RotAccelAbsoluteTypeIce in) {
		return ((in !=null)?fromIce(in, new RotAccelAbsoluteType()):null);
	}

	public static RotAccelAbsoluteType fromIce(RotAccelAbsoluteTypeIce in,RotAccelAbsoluteType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotAccelAbsoluteType) fromIce((RotAccelTypeIce)in,(RotAccelType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		return out;
	}

	public static SetMotionCoordinationType fromIce(SetMotionCoordinationTypeIce in) {
		return ((in !=null)?fromIce(in, new SetMotionCoordinationType()):null);
	}

	public static SetMotionCoordinationType fromIce(SetMotionCoordinationTypeIce in,SetMotionCoordinationType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetMotionCoordinationType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setCoordinated(in.coordinated);
		return out;
	}

	public static ActuateJointType fromIce(ActuateJointTypeIce in) {
		return ((in !=null)?fromIce(in, new ActuateJointType()):null);
	}

	public static ActuateJointType fromIce(ActuateJointTypeIce in,ActuateJointType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ActuateJointType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.jointPositionIsNull) {
			out.setJointPosition(null);
		} else {
			out.setJointPosition(BigDecimal.valueOf(in.jointPosition));
		}
		if(in.jointNumberIsNull) {
			out.setJointNumber(null);
		} else {
			out.setJointNumber(BigInteger.valueOf(in.jointNumber));
		}
		out.setJointDetails(fromIce(in.jointDetails,out.getJointDetails()));
		return out;
	}
	public static StopConditionEnumType fromIce(StopConditionEnumTypeIce in) {
		return StopConditionEnumType.values()[in.value()];
	}

	public static RotSpeedAbsoluteType fromIce(RotSpeedAbsoluteTypeIce in) {
		return ((in !=null)?fromIce(in, new RotSpeedAbsoluteType()):null);
	}

	public static RotSpeedAbsoluteType fromIce(RotSpeedAbsoluteTypeIce in,RotSpeedAbsoluteType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (RotSpeedAbsoluteType) fromIce((RotSpeedTypeIce)in,(RotSpeedType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		return out;
	}

	public static ThreeFingerGripperStatusType fromIce(ThreeFingerGripperStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new ThreeFingerGripperStatusType()):null);
	}

	public static ThreeFingerGripperStatusType fromIce(ThreeFingerGripperStatusTypeIce in,ThreeFingerGripperStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (ThreeFingerGripperStatusType) fromIce((GripperStatusTypeIce)in,(GripperStatusType)out);
		if(in.finger1PositionIsNull) {
			out.setFinger1Position(null);
		} else {
			out.setFinger1Position(BigDecimal.valueOf(in.finger1Position));
		}
		if(in.finger2PositionIsNull) {
			out.setFinger2Position(null);
		} else {
			out.setFinger2Position(BigDecimal.valueOf(in.finger2Position));
		}
		if(in.finger2ForceIsNull) {
			out.setFinger2Force(null);
		} else {
			out.setFinger2Force(BigDecimal.valueOf(in.finger2Force));
		}
		if(in.finger1ForceIsNull) {
			out.setFinger1Force(null);
		} else {
			out.setFinger1Force(BigDecimal.valueOf(in.finger1Force));
		}
		if(in.finger3ForceIsNull) {
			out.setFinger3Force(null);
		} else {
			out.setFinger3Force(BigDecimal.valueOf(in.finger3Force));
		}
		if(in.finger3PositionIsNull) {
			out.setFinger3Position(null);
		} else {
			out.setFinger3Position(BigDecimal.valueOf(in.finger3Position));
		}
		return out;
	}

	public static SetRobotParametersType fromIce(SetRobotParametersTypeIce in) {
		return ((in !=null)?fromIce(in, new SetRobotParametersType()):null);
	}

	public static SetRobotParametersType fromIce(SetRobotParametersTypeIce in,SetRobotParametersType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetRobotParametersType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.getParameterSetting().clear();
		out.getParameterSetting().addAll(fromIce(in.parameterSetting));
		return out;
	}

	public static MoveToType fromIce(MoveToTypeIce in) {
		return ((in !=null)?fromIce(in, new MoveToType()):null);
	}

	public static MoveToType fromIce(MoveToTypeIce in,MoveToType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (MoveToType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setMoveStraight(in.moveStraight);
		out.setEndPosition(fromIce(in.endPosition,out.getEndPosition()));
		return out;
	}

	public static ObjectFactory fromIce(ObjectFactoryIce in) {
		return ((in !=null)?fromIce(in, new ObjectFactory()):null);
	}

	public static ObjectFactory fromIce(ObjectFactoryIce in,ObjectFactory out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		return out;
	}

	public static PoseAndSetType fromIce(PoseAndSetTypeIce in) {
		return ((in !=null)?fromIce(in, new PoseAndSetType()):null);
	}

	public static PoseAndSetType fromIce(PoseAndSetTypeIce in,PoseAndSetType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (PoseAndSetType) fromIce((PoseTypeIce)in,(PoseType)out);
		out.setTransSpeed(fromIce(in.transSpeed,out.getTransSpeed()));
		out.setRotAccel(fromIce(in.rotAccel,out.getRotAccel()));
		out.setTolerance(fromIce(in.tolerance,out.getTolerance()));
		out.setCoordinated(in.coordinated);
		out.setRotSpeed(fromIce(in.rotSpeed,out.getRotSpeed()));
		out.setTransAccel(fromIce(in.transAccel,out.getTransAccel()));
		return out;
	}

	public static SettingsStatusType fromIce(SettingsStatusTypeIce in) {
		return ((in !=null)?fromIce(in, new SettingsStatusType()):null);
	}

	public static SettingsStatusType fromIce(SettingsStatusTypeIce in,SettingsStatusType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SettingsStatusType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		out.setTransSpeedAbsolute(fromIce(in.transSpeedAbsolute,out.getTransSpeedAbsolute()));
		out.setForceUnitName(fromIce(in.forceUnitName));
		out.setTransAccelAbsolute(fromIce(in.transAccelAbsolute,out.getTransAccelAbsolute()));
		out.getEndEffectorParameterSetting().clear();
		out.getEndEffectorParameterSetting().addAll(fromIce(in.endEffectorParameterSetting));
		out.setTorqueUnitName(fromIce(in.torqueUnitName));
		if(in.endEffectorSettingIsNull) {
			out.setEndEffectorSetting(null);
		} else {
			out.setEndEffectorSetting(BigDecimal.valueOf(in.endEffectorSetting));
		}
		out.setRotAccelRelative(fromIce(in.rotAccelRelative,out.getRotAccelRelative()));
		out.getJointLimits().clear();
		out.getJointLimits().addAll(fromIce(in.jointLimits));
		out.setIntermediatePoseTolerance(fromIce(in.intermediatePoseTolerance,out.getIntermediatePoseTolerance()));
		if(in.motionCoordinatedIsNull) {
			out.setMotionCoordinated(null);
		} else {
			out.setMotionCoordinated(in.motionCoordinated);
		}
		out.setMinCartesianLimit(fromIce(in.minCartesianLimit,out.getMinCartesianLimit()));
		out.setRotSpeedAbsolute(fromIce(in.rotSpeedAbsolute,out.getRotSpeedAbsolute()));
		out.setMaxCartesianLimit(fromIce(in.maxCartesianLimit,out.getMaxCartesianLimit()));
		out.setLengthUnitName(fromIce(in.lengthUnitName));
		out.setPoseTolerance(fromIce(in.poseTolerance,out.getPoseTolerance()));
		out.setTransSpeedRelative(fromIce(in.transSpeedRelative,out.getTransSpeedRelative()));
		out.setRotSpeedRelative(fromIce(in.rotSpeedRelative,out.getRotSpeedRelative()));
		out.setTransAccelRelative(fromIce(in.transAccelRelative,out.getTransAccelRelative()));
		out.setAngleUnitName(fromIce(in.angleUnitName));
		out.getRobotParameterSetting().clear();
		out.getRobotParameterSetting().addAll(fromIce(in.robotParameterSetting));
		out.setRotAccelAbsolute(fromIce(in.rotAccelAbsolute,out.getRotAccelAbsolute()));
		return out;
	}

	public static TransAccelRelativeType fromIce(TransAccelRelativeTypeIce in) {
		return ((in !=null)?fromIce(in, new TransAccelRelativeType()):null);
	}

	public static TransAccelRelativeType fromIce(TransAccelRelativeTypeIce in,TransAccelRelativeType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (TransAccelRelativeType) fromIce((TransAccelTypeIce)in,(TransAccelType)out);
		if(in.fractionIsNull) {
			out.setFraction(null);
		} else {
			out.setFraction(BigDecimal.valueOf(in.fraction));
		}
		return out;
	}

	public static DwellType fromIce(DwellTypeIce in) {
		return ((in !=null)?fromIce(in, new DwellType()):null);
	}

	public static DwellType fromIce(DwellTypeIce in,DwellType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (DwellType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		if(in.dwellTimeIsNull) {
			out.setDwellTime(null);
		} else {
			out.setDwellTime(BigDecimal.valueOf(in.dwellTime));
		}
		return out;
	}

	public static SetLengthUnitsType fromIce(SetLengthUnitsTypeIce in) {
		return ((in !=null)?fromIce(in, new SetLengthUnitsType()):null);
	}

	public static SetLengthUnitsType fromIce(SetLengthUnitsTypeIce in,SetLengthUnitsType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetLengthUnitsType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setUnitName(fromIce(in.unitName));
		return out;
	}

	public static OpenToolChangerType fromIce(OpenToolChangerTypeIce in) {
		return ((in !=null)?fromIce(in, new OpenToolChangerType()):null);
	}

	public static OpenToolChangerType fromIce(OpenToolChangerTypeIce in,OpenToolChangerType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (OpenToolChangerType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		return out;
	}

	public static SetTransAccelType fromIce(SetTransAccelTypeIce in) {
		return ((in !=null)?fromIce(in, new SetTransAccelType()):null);
	}

	public static SetTransAccelType fromIce(SetTransAccelTypeIce in,SetTransAccelType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (SetTransAccelType) fromIce((MiddleCommandTypeIce)in,(MiddleCommandType)out);
		out.setTransAccel(fromIce(in.transAccel,out.getTransAccel()));
		return out;
	}

	public static JointForceTorqueType fromIce(JointForceTorqueTypeIce in) {
		return ((in !=null)?fromIce(in, new JointForceTorqueType()):null);
	}

	public static JointForceTorqueType fromIce(JointForceTorqueTypeIce in,JointForceTorqueType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (JointForceTorqueType) fromIce((JointDetailsTypeIce)in,(JointDetailsType)out);
		if(in.settingIsNull) {
			out.setSetting(null);
		} else {
			out.setSetting(BigDecimal.valueOf(in.setting));
		}
		if(in.changeRateIsNull) {
			out.setChangeRate(null);
		} else {
			out.setChangeRate(BigDecimal.valueOf(in.changeRate));
		}
		return out;
	}

	public static PointType fromIce(PointTypeIce in) {
		return ((in !=null)?fromIce(in, new PointType()):null);
	}

	public static PointType fromIce(PointTypeIce in,PointType out) {
		if(in == null) {
			return null;
		}
		if(out == null) {
			return fromIce(in);
		}
		out = (PointType) fromIce((DataThingTypeIce)in,(DataThingType)out);
		if(in.zIsNull) {
			out.setZ(null);
		} else {
			out.setZ(BigDecimal.valueOf(in.z));
		}
		if(in.yIsNull) {
			out.setY(null);
		} else {
			out.setY(BigDecimal.valueOf(in.y));
		}
		if(in.xIsNull) {
			out.setX(null);
		} else {
			out.setX(BigDecimal.valueOf(in.x));
		}
		return out;
	}
	public static CommandStateEnumType fromIce(CommandStateEnumTypeIce in) {
		return CommandStateEnumType.values()[in.value()];
	}
}
