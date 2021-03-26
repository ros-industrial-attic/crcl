package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Constants for LM Codes.
 * </p>
 */
public enum FRELMCodeConstants implements ComEnum {
  /**
   * <p>
   *  NONE
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_NONE_C(0),
  /**
   * <p>
   *  RESERVED DO NOT CHANGE
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SAMPLE_C(0),
  /**
   * <p>
   *  no item
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_NOITM_C(0),
  /**
   * <p>
   *  constant
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_CONST_C(1),
  /**
   * <p>
   *  index
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_INDEX_C(2),
  /**
   * <p>
   *  Register
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_REG_C(3),
  /**
   * <p>
   *  Position Register
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_PREG_C(4),
  /**
   * <p>
   *  Position Item
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_POSITEM_C(5),
  /**
   * <p>
   *  Pallet Register
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_PLREG_C(6),
  /**
   * <p>
   *  Pallet Element
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_PLELM_C(7),
  /**
   * <p>
   *  SDI
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  LM_SDI_C(10),
  /**
   * <p>
   *  RDI
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  LM_RDI_C(11),
  /**
   * <p>
   *  AIN
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  LM_AIN_C(12),
  /**
   * <p>
   *  WI
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  LM_WI_C(13),
  /**
   * <p>
   *  SDO
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  LM_SDO_C(15),
  /**
   * <p>
   *  RDO
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  LM_RDO_C(16),
  /**
   * <p>
   *  AOUT
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  LM_AOUT_C(17),
  /**
   * <p>
   *  WO
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  LM_WO_C(18),
  /**
   * <p>
   *  RSR
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  LM_RSR_C(20),
  /**
   * <p>
   *  UALM
   * </p>
   * <p>
   * The value of this constant is 21
   * </p>
   */
  LM_UALM_C(21),
  /**
   * <p>
   *  CODE (input)
   * </p>
   * <p>
   * The value of this constant is 22
   * </p>
   */
  LM_CODEI_C(22),
  /**
   * <p>
   *  CODE (output)
   * </p>
   * <p>
   * The value of this constant is 23
   * </p>
   */
  LM_CODEO_C(23),
  /**
   * <p>
   *  OVERRIDE
   * </p>
   * <p>
   * The value of this constant is 24
   * </p>
   */
  LM_OVRD_C(24),
  /**
   * <p>
   *  TIMER
   * </p>
   * <p>
   * The value of this constant is 25
   * </p>
   */
  LM_TIMER_C(25),
  /**
   * <p>
   *  parameter
   * </p>
   * <p>
   * The value of this constant is 26
   * </p>
   */
  LM_PARAM_C(26),
  /**
   * <p>
   *  error program
   * </p>
   * <p>
   * The value of this constant is 27
   * </p>
   */
  LM_ERPRG_C(27),
  /**
   * <p>
   *  resume program
   * </p>
   * <p>
   * The value of this constant is 28
   * </p>
   */
  LM_RSMPRG_C(28),
  /**
   * <p>
   *  semaphore
   * </p>
   * <p>
   * The value of this constant is 29
   * </p>
   */
  LM_SEMPH_C(29),
  /**
   * <p>
   *  comment
   * </p>
   * <p>
   * The value of this constant is 30
   * </p>
   */
  LM_REM_C(30),
  /**
   * <p>
   *  MESSAGE
   * </p>
   * <p>
   * The value of this constant is 31
   * </p>
   */
  LM_MSG_C(31),
  /**
   * <p>
   *  user frame selection
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  LM_UFRNUM_C(32),
  /**
   * <p>
   *  user frame
   * </p>
   * <p>
   * The value of this constant is 33
   * </p>
   */
  LM_UFRAME_C(33),
  /**
   * <p>
   *  user tool selection
   * </p>
   * <p>
   * The value of this constant is 34
   * </p>
   */
  LM_UTLNUM_C(34),
  /**
   * <p>
   *  user tool frame
   * </p>
   * <p>
   * The value of this constant is 35
   * </p>
   */
  LM_UTOOL_C(35),
  /**
   * <p>
   *  SI
   * </p>
   * <p>
   * The value of this constant is 36
   * </p>
   */
  LM_SI_C(36),
  /**
   * <p>
   *  UI
   * </p>
   * <p>
   * The value of this constant is 37
   * </p>
   */
  LM_UI_C(37),
  /**
   * <p>
   *  SO
   * </p>
   * <p>
   * The value of this constant is 38
   * </p>
   */
  LM_SO_C(38),
  /**
   * <p>
   *  UO
   * </p>
   * <p>
   * The value of this constant is 39
   * </p>
   */
  LM_UO_C(39),
  /**
   * <p>
   *  Error number
   * </p>
   * <p>
   * The value of this constant is 40
   * </p>
   */
  LM_ERNUM_C(40),
  /**
   * <p>
   *  special constant
   * </p>
   * <p>
   * The value of this constant is 50
   * </p>
   */
  LM_SPCON_C(50),
  /**
   * <p>
   *  pulse constant
   * </p>
   * <p>
   * The value of this constant is 51
   * </p>
   */
  LM_PULSE_C(51),
  /**
   * <p>
   *  lock pos register
   * </p>
   * <p>
   * The value of this constant is 52
   * </p>
   */
  LM_LKPREG_C(52),
  /**
   * <p>
   *  unlock pos register
   * </p>
   * <p>
   * The value of this constant is 53
   * </p>
   */
  LM_ULPREG_C(53),
  /**
   * <p>
   *  Clear resume program
   * </p>
   * <p>
   * The value of this constant is 54
   * </p>
   */
  LM_CLRSMPRG_C(54),
  /**
   * <p>
   *  Return path disable
   * </p>
   * <p>
   * The value of this constant is 55
   * </p>
   */
  LM_ORGDSBL_C(55),
  /**
   * <p>
   *  Pressure
   * </p>
   * <p>
   * The value of this constant is 56
   * </p>
   */
  LM_PRESSURE_C(56),
  /**
   * <p>
   *  Timer overflow
   * </p>
   * <p>
   * The value of this constant is 57
   * </p>
   */
  LM_TIMOVR_C(57),
  /**
   * <p>
   *  Max speed
   * </p>
   * <p>
   * The value of this constant is 58
   * </p>
   */
  LM_MAXSPD_C(58),
  /**
   * <p>
   *  Data monitor
   * </p>
   * <p>
   * The value of this constant is 59
   * </p>
   */
  LM_DMON_C(59),
  /**
   * <p>
   *  Maint. program
   * </p>
   * <p>
   * The value of this constant is 60
   * </p>
   */
  LM_MNTPRG_C(60),
  /**
   * <p>
   *  Call argument
   * </p>
   * <p>
   * The value of this constant is 61
   * </p>
   */
  LM_ARG_C(61),
  /**
   * <p>
   *  Argument: Register
   * </p>
   * <p>
   * The value of this constant is 62
   * </p>
   */
  LM_ARGREG_C(62),
  /**
   * <p>
   *  Argument: String
   * </p>
   * <p>
   * The value of this constant is 63
   * </p>
   */
  LM_ARGSTR_C(63),
  /**
   * <p>
   *  Argument terminator
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  LM_ARGTERM_C(64),
  /**
   * <p>
   *  Serrvo gun general
   * </p>
   * <p>
   * The value of this constant is 65
   * </p>
   */
  LM_SVGGEN_C(65),
  /**
   * <p>
   *  TCP Speed Output
   * </p>
   * <p>
   * The value of this constant is 66
   * </p>
   */
  LM_TCPSPD_C(66),
  /**
   * <p>
   *  = (assgin)
   * </p>
   * <p>
   * The value of this constant is 100
   * </p>
   */
  LM_ASGN_C(100),
  /**
   * <p>
   *  +
   * </p>
   * <p>
   * The value of this constant is 101
   * </p>
   */
  LM_PLUS_C(101),
  /**
   * <p>
   *  -
   * </p>
   * <p>
   * The value of this constant is 102
   * </p>
   */
  LM_MINUS_C(102),
  /**
   * <p>
   *  *
   * </p>
   * <p>
   * The value of this constant is 103
   * </p>
   */
  LM_MULT_C(103),
  /**
   * <p>
   *  /
   * </p>
   * <p>
   * The value of this constant is 104
   * </p>
   */
  LM_DIV_C(104),
  /**
   * <p>
   *  DIV
   * </p>
   * <p>
   * The value of this constant is 105
   * </p>
   */
  LM_DIVI_C(105),
  /**
   * <p>
   *  MOD
   * </p>
   * <p>
   * The value of this constant is 106
   * </p>
   */
  LM_MOD_C(106),
  /**
   * <p>
   *  = (compare)
   * </p>
   * <p>
   * The value of this constant is 107
   * </p>
   */
  LM_EQ_C(107),
  /**
   * <p>
   *  <>
   * </p>
   * <p>
   * The value of this constant is 108
   * </p>
   */
  LM_NE_C(108),
  /**
   * <p>
   *  <
   * </p>
   * <p>
   * The value of this constant is 109
   * </p>
   */
  LM_LT_C(109),
  /**
   * <p>
   *  <=
   * </p>
   * <p>
   * The value of this constant is 110
   * </p>
   */
  LM_LE_C(110),
  /**
   * <p>
   *  >
   * </p>
   * <p>
   * The value of this constant is 111
   * </p>
   */
  LM_GT_C(111),
  /**
   * <p>
   *  >=
   * </p>
   * <p>
   * The value of this constant is 112
   * </p>
   */
  LM_GE_C(112),
  /**
   * <p>
   *  AND
   * </p>
   * <p>
   * The value of this constant is 113
   * </p>
   */
  LM_AND_C(113),
  /**
   * <p>
   *  OR
   * </p>
   * <p>
   * The value of this constant is 114
   * </p>
   */
  LM_OR_C(114),
  /**
   * <p>
   *  (
   * </p>
   * <p>
   * The value of this constant is 115
   * </p>
   */
  LM_L_PAREN_C(115),
  /**
   * <p>
   *  )
   * </p>
   * <p>
   * The value of this constant is 116
   * </p>
   */
  LM_R_PAREN_C(116),
  /**
   * <p>
   *  !
   * </p>
   * <p>
   * The value of this constant is 117
   * </p>
   */
  LM_NOT_C(117),
  /**
   * <p>
   *  IF
   * </p>
   * <p>
   * The value of this constant is 120
   * </p>
   */
  LM_IF_C(120),
  /**
   * <p>
   *  SELECT
   * </p>
   * <p>
   * The value of this constant is 121
   * </p>
   */
  LM_SEL_C(121),
  /**
   * <p>
   *  OTHERWISE
   * </p>
   * <p>
   * The value of this constant is 122
   * </p>
   */
  LM_OTHWS_C(122),
  /**
   * <p>
   *  WAIT (delay)
   * </p>
   * <p>
   * The value of this constant is 123
   * </p>
   */
  LM_DELAY_C(123),
  /**
   * <p>
   *  WAIT (condition)
   * </p>
   * <p>
   * The value of this constant is 124
   * </p>
   */
  LM_WAIT_C(124),
  /**
   * <p>
   *  TIMEOUT
   * </p>
   * <p>
   * The value of this constant is 125
   * </p>
   */
  LM_TMOUT_C(125),
  /**
   * <p>
   *  JMP
   * </p>
   * <p>
   * The value of this constant is 126
   * </p>
   */
  LM_JMP_C(126),
  /**
   * <p>
   *  LBL (specifier)
   * </p>
   * <p>
   * The value of this constant is 127
   * </p>
   */
  LM_JMPDS_C(127),
  /**
   * <p>
   *  LBL (definition)
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  LM_LBL_C(128),
  /**
   * <p>
   *  CALL
   * </p>
   * <p>
   * The value of this constant is 129
   * </p>
   */
  LM_CALL_C(129),
  /**
   * <p>
   *  program name
   * </p>
   * <p>
   * The value of this constant is 130
   * </p>
   */
  LM_PGNAM_C(130),
  /**
   * <p>
   *  PAUSE
   * </p>
   * <p>
   * The value of this constant is 131
   * </p>
   */
  LM_PAUSE_C(131),
  /**
   * <p>
   *  ABORT
   * </p>
   * <p>
   * The value of this constant is 132
   * </p>
   */
  LM_ABORT_C(132),
  /**
   * <p>
   *  END
   * </p>
   * <p>
   * The value of this constant is 133
   * </p>
   */
  LM_END_C(133),
  /**
   * <p>
   *  RUN
   * </p>
   * <p>
   * The value of this constant is 134
   * </p>
   */
  LM_RUN_C(134),
  /**
   * <p>
   *  MACRO
   * </p>
   * <p>
   * The value of this constant is 135
   * </p>
   */
  LM_MACRO_C(135),
  /**
   * <p>
   *  VEIN ON/OFF
   * </p>
   * <p>
   * The value of this constant is 136
   * </p>
   */
  LM_VEIN_C(136),
  /**
   * <p>
   *  FIGURE CUTTING
   * </p>
   * <p>
   * The value of this constant is 137
   * </p>
   */
  LM_FC_C(137),
  /**
   * <p>
   *  HIGHT SENSOR
   * </p>
   * <p>
   * The value of this constant is 138
   * </p>
   */
  LM_HTSNS_C(138),
  /**
   * <p>
   *  OCP START/END
   * </p>
   * <p>
   * The value of this constant is 139
   * </p>
   */
  LM_OCP_C(139),
  /**
   * <p>
   *  SEND
   * </p>
   * <p>
   * The value of this constant is 140
   * </p>
   */
  LM_SEND_C(140),
  /**
   * <p>
   *  RECEIVE
   * </p>
   * <p>
   * The value of this constant is 141
   * </p>
   */
  LM_RCV_C(141),
  /**
   * <p>
   *  CALMATRIX
   * </p>
   * <p>
   * The value of this constant is 142
   * </p>
   */
  LM_CALM_C(142),
  /**
   * <p>
   *  SENSOR_ON[...]
   * </p>
   * <p>
   * The value of this constant is 143
   * </p>
   */
  LM_SNSON_C(143),
  /**
   * <p>
   *  SENSOR_OFF
   * </p>
   * <p>
   * The value of this constant is 144
   * </p>
   */
  LM_SNSOF_C(144),
  /**
   * <p>
   *  SEARCH_MIGEYE[...]
   * </p>
   * <p>
   * The value of this constant is 145
   * </p>
   */
  LM_SMIGE_C(145),
  /**
   * <p>
   *  TORQUE LIMIT ... %
   * </p>
   * <p>
   * The value of this constant is 146
   * </p>
   */
  LM_TORQ_C(146),
  /**
   * <p>
   *  MONITOR/END MONITOR
   * </p>
   * <p>
   * The value of this constant is 147
   * </p>
   */
  LM_MONITOR_C(147),
  /**
   * <p>
   *  WHEN
   * </p>
   * <p>
   * The value of this constant is 148
   * </p>
   */
  LM_WHEN_C(148),
  /**
   * <p>
   *  PAYLOAD SETTING
   * </p>
   * <p>
   * The value of this constant is 149
   * </p>
   */
  LM_PLST_C(149),
  /**
   * <p>
   *  process logic
   * </p>
   * <p>
   * The value of this constant is 150
   * </p>
   */
  LM_APPL_C(150),
  /**
   * <p>
   *  process logic
   * </p>
   * <p>
   * The value of this constant is 151
   * </p>
   */
  LM_APPL1_C(151),
  /**
   * <p>
   *  process logic
   * </p>
   * <p>
   * The value of this constant is 152
   * </p>
   */
  LM_APPL2_C(152),
  /**
   * <p>
   *  MIG EYE
   * </p>
   * <p>
   * The value of this constant is 160
   * </p>
   */
  LM_MIG_C(160),
  /**
   * <p>
   *  V-400i vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 161
   * </p>
   */
  LM_VISION_C(161),
  /**
   * <p>
   *  IM = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 162
   * </p>
   */
  LM_VIS_IM_C(162),
  /**
   * <p>
   *  ST = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 163
   * </p>
   */
  LM_VIS_ST_C(163),
  /**
   * <p>
   *  ID = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 164
   * </p>
   */
  LM_VIS_ID_C(164),
  /**
   * <p>
   *  FL = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 165
   * </p>
   */
  LM_VIS_FL_C(165),
  /**
   * <p>
   *  RS = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 166
   * </p>
   */
  LM_VIS_RS_C(166),
  /**
   * <p>
   *  OF = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 167
   * </p>
   */
  LM_VIS_OF_C(167),
  /**
   * <p>
   *  QN = x vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 168
   * </p>
   */
  LM_VIS_QN_C(168),
  /**
   * <p>
   *  VIEW[...] vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 169
   * </p>
   */
  LM_VIS_VW_C(169),
  /**
   * <p>
   *  Compliance control
   * </p>
   * <p>
   * The value of this constant is 170
   * </p>
   */
  LM_CC_C(170),
  /**
   * <p>
   *  ASCII INTERFACE
   * </p>
   * <p>
   * The value of this constant is 171
   * </p>
   */
  LM_IBGNSTRT_C(171),
  /**
   * <p>
   *  ASCII INTERFACE
   * </p>
   * <p>
   * The value of this constant is 172
   * </p>
   */
  LM_IBGNEND_C(172),
  /**
   * <p>
   *  ASCII INTERFACE
   * </p>
   * <p>
   * The value of this constant is 173
   * </p>
   */
  LM_IBGNREC_C(173),
  /**
   * <p>
   *  ASCII INTERFACE
   * </p>
   * <p>
   * The value of this constant is 174
   * </p>
   */
  LM_IBGNRECE_C(174),
  /**
   * <p>
   *  Servo gun change
   * </p>
   * <p>
   * The value of this constant is 175
   * </p>
   */
  LM_SVGC_C(175),
  /**
   * <p>
   *  Servo Hand change
   * </p>
   * <p>
   * The value of this constant is 176
   * </p>
   */
  LM_SVHC_C(176),
  /**
   * <p>
   *  Servo Tool change
   * </p>
   * <p>
   * The value of this constant is 176
   * </p>
   */
  LM_SVTC_C(176),
  /**
   * <p>
   *  InterBus-S change
   * </p>
   * <p>
   * The value of this constant is 177
   * </p>
   */
  LM_IBSC_C(177),
  /**
   * <p>
   *  InterBus-S change
   * </p>
   * <p>
   * The value of this constant is 178
   * </p>
   */
  LM_GRPNAME_C(178),
  /**
   * <p>
   *  InterBus PCI segment switching
   * </p>
   * <p>
   * The value of this constant is 179
   * </p>
   */
  LM_IBPX_C(179),
  /**
   * <p>
   *  No application manager
   * </p>
   * <p>
   * The value of this constant is 180
   * </p>
   */
  LM_NOAMR_APPL_C(180),
  /**
   * <p>
   *  3D vision
   * </p>
   * <p>
   * The value of this constant is 190
   * </p>
   */
  LM_IV_C(190),
  /**
   * <p>
   *  SC = *: 3DV, Schedule Number 
   * </p>
   * <p>
   * The value of this constant is 191
   * </p>
   */
  LM_IV_SC_C(191),
  /**
   * <p>
   *  PS = *: 3DV, Schedule Number 
   * </p>
   * <p>
   * The value of this constant is 192
   * </p>
   */
  LM_IV_PS_C(192),
  /**
   * <p>
   *  2D vision
   * </p>
   * <p>
   * The value of this constant is 193
   * </p>
   */
  LM_PCVIS_C(193),
  /**
   * <p>
   *  ST = x, 2D vision status reg num
   * </p>
   * <p>
   * The value of this constant is 194
   * </p>
   */
  LM_PCVIS_ST_C(194),
  /**
   * <p>
   *  OF = x, 2D vision offset reg num
   * </p>
   * <p>
   * The value of this constant is 195
   * </p>
   */
  LM_PCVIS_OF_C(195),
  /**
   * <p>
   *  2D vision sensor integrated type
   * </p>
   * <p>
   * The value of this constant is 196
   * </p>
   */
  LM_SIVIS__C(196),
  /**
   * <p>
   *  ST = x, 2D vision offset reg num
   * </p>
   * <p>
   * The value of this constant is 197
   * </p>
   */
  LM_SIVIS_ST_C(197),
  /**
   * <p>
   *  OF = x, 2D vision offset reg num
   * </p>
   * <p>
   * The value of this constant is 198
   * </p>
   */
  LM_SIVIS_OF_C(198),
  /**
   * <p>
   *  Wrist joint
   * </p>
   * <p>
   * The value of this constant is 200
   * </p>
   */
  LM_WJNT_C(200),
  /**
   * <p>
   *  Coordinated motion
   * </p>
   * <p>
   * The value of this constant is 201
   * </p>
   */
  LM_COORD_C(201),
  /**
   * <p>
   *  SKIP
   * </p>
   * <p>
   * The value of this constant is 202
   * </p>
   */
  LM_SKIP_C(202),
  /**
   * <p>
   *  SKIP CONDITION
   * </p>
   * <p>
   * The value of this constant is 203
   * </p>
   */
  LM_SKPCND_C(203),
  /**
   * <p>
   *  QUICK SKIP
   * </p>
   * <p>
   * The value of this constant is 204
   * </p>
   */
  LM_QSKP_C(204),
  /**
   * <p>
   *  OFFSET
   * </p>
   * <p>
   * The value of this constant is 205
   * </p>
   */
  LM_OFST_C(205),
  /**
   * <p>
   *  OFFSET for via point
   * </p>
   * <p>
   * The value of this constant is 206
   * </p>
   */
  LM_VIAOFS_C(206),
  /**
   * <p>
   *  OFFSET CONDITION
   * </p>
   * <p>
   * The value of this constant is 207
   * </p>
   */
  LM_OFSCND_C(207),
  /**
   * <p>
   *  SOFT FLOAT
   * </p>
   * <p>
   * The value of this constant is 208
   * </p>
   */
  LM_SFLT_C(208),
  /**
   * <p>
   *  STITCH
   * </p>
   * <p>
   * The value of this constant is 209
   * </p>
   */
  LM_STITCH_C(209),
  /**
   * <p>
   *  Incremental motion
   * </p>
   * <p>
   * The value of this constant is 210
   * </p>
   */
  LM_INC_C(210),
  /**
   * <p>
   *  Acceleation override
   * </p>
   * <p>
   * The value of this constant is 211
   * </p>
   */
  LM_ACC_C(211),
  /**
   * <p>
   *  TOUCH SENSOR
   * </p>
   * <p>
   * The value of this constant is 212
   * </p>
   */
  LM_TOUCH_C(212),
  /**
   * <p>
   *  CORNER_TOL
   * </p>
   * <p>
   * The value of this constant is 213
   * </p>
   */
  LM_CT_C(213),
  /**
   * <p>
   *  Cell Finder
   * </p>
   * <p>
   * The value of this constant is 214
   * </p>
   */
  LM_CELL_C(214),
  /**
   * <p>
   *  WS
   * </p>
   * <p>
   * The value of this constant is 215
   * </p>
   */
  LM_WS_C(215),
  /**
   * <p>
   *  WE (Obsolete)
   * </p>
   * <p>
   * The value of this constant is 216
   * </p>
   */
  LM_WE_C(216),
  /**
   * <p>
   *  TRACK
   * </p>
   * <p>
   * The value of this constant is 216
   * </p>
   */
  LM_TRACK_C(216),
  /**
   * <p>
   *  Motion Cycle Time
   * </p>
   * <p>
   * The value of this constant is 217
   * </p>
   */
  LM_MCT_C(217),
  /**
   * <p>
   *  Line Tracking
   * </p>
   * <p>
   * The value of this constant is 218
   * </p>
   */
  LM_LNT_C(218),
  /**
   * <p>
   *  TOOL OFFSET
   * </p>
   * <p>
   * The value of this constant is 219
   * </p>
   */
  LM_TOFST_C(219),
  /**
   * <p>
   *  TOOL OFFSET for via point
   * </p>
   * <p>
   * The value of this constant is 220
   * </p>
   */
  LM_TVIAOFS_C(220),
  /**
   * <p>
   *  TOOL OFFSET CONDITION
   * </p>
   * <p>
   * The value of this constant is 221
   * </p>
   */
  LM_TOFSCND_C(221),
  /**
   * <p>
   *  Continuous turn
   * </p>
   * <p>
   * The value of this constant is 222
   * </p>
   */
  LM_CN_C(222),
  /**
   * <p>
   *  STICK DETECTION
   * </p>
   * <p>
   * The value of this constant is 223
   * </p>
   */
  LM_STCK_C(223),
  /**
   * <p>
   *  High Sensitive Collision Detection
   * </p>
   * <p>
   * The value of this constant is 224
   * </p>
   */
  LM_HSCD_C(224),
  /**
   * <p>
   *  LOAD CLUTCH
   * </p>
   * <p>
   * The value of this constant is 225
   * </p>
   */
  LM_LDCL_C(225),
  /**
   * <p>
   *  Short Cut Path
   * </p>
   * <p>
   * The value of this constant is 226
   * </p>
   */
  LM_SCP_C(226),
  /**
   * <p>
   *  Simple palletizing
   * </p>
   * <p>
   * The value of this constant is 227
   * </p>
   */
  LM_PAL2_C(227),
  /**
   * <p>
   *  Servo Gun
   * </p>
   * <p>
   * The value of this constant is 228
   * </p>
   */
  LM_SERVOGUN_C(228),
  /**
   * <p>
   *  Servo gun pressure
   * </p>
   * <p>
   * The value of this constant is 229
   * </p>
   */
  LM_SVGPRES_C(229),
  /**
   * <p>
   *  Orientation Fixed
   * </p>
   * <p>
   * The value of this constant is 230
   * </p>
   */
  LM_OFIX_C(230),
  /**
   * <p>
   *  Approach_STOP/WAIT
   * </p>
   * <p>
   * The value of this constant is 231
   * </p>
   */
  LM_IAINST_C(231),
  /**
   * <p>
   *  For frl iwc(dg)
   * </p>
   * <p>
   * The value of this constant is 232
   * </p>
   */
  LM_IWC_C(232),
  /**
   * <p>
   *  Start Linear Distance
   * </p>
   * <p>
   * The value of this constant is 233
   * </p>
   */
  LM_LD_STRT_C(233),
  /**
   * <p>
   *  End Linear Distance
   * </p>
   * <p>
   * The value of this constant is 234
   * </p>
   */
  LM_LD_END_C(234),
  /**
   * <p>
   *  Motion Squeeze
   * </p>
   * <p>
   * The value of this constant is 235
   * </p>
   */
  LM_MSQZ_C(235),
  /**
   * <p>
   *  Path Optimization
   * </p>
   * <p>
   * The value of this constant is 236
   * </p>
   */
  LM_OPTP_C(236),
  /**
   * <p>
   *  PSPD
   * </p>
   * <p>
   * The value of this constant is 237
   * </p>
   */
  LM_CPPSPD_C(237),
  /**
   * <p>
   *  BREAK
   * </p>
   * <p>
   * The value of this constant is 238
   * </p>
   */
  LM_CPBRK_C(238),
  /**
   * <p>
   *  CONCURRENT
   * </p>
   * <p>
   * The value of this constant is 240
   * </p>
   */
  LM_CONCR_C(240),
  /**
   * <p>
   *  ENDCONCUR
   * </p>
   * <p>
   * The value of this constant is 241
   * </p>
   */
  LM_ENDCN_C(241),
  /**
   * <p>
   *  motion group mask
   * </p>
   * <p>
   * The value of this constant is 242
   * </p>
   */
  LM_MOGRP_C(242),
  /**
   * <p>
   *  EV (Extended Axes Speed)
   * </p>
   * <p>
   * The value of this constant is 243
   * </p>
   */
  LM_AUXF_C(243),
  /**
   * <p>
   *  PTH
   * </p>
   * <p>
   * The value of this constant is 244
   * </p>
   */
  LM_PTH_C(244),
  /**
   * <p>
   *  RTCP
   * </p>
   * <p>
   * The value of this constant is 245
   * </p>
   */
  LM_RTCP_C(245),
  /**
   * <p>
   *  TIME BEFORE and TIME AFTER
   * </p>
   * <p>
   * The value of this constant is 246
   * </p>
   */
  LM_MOPTIME_C(246),
  /**
   * <p>
   *  CD (Corner Distance)
   * </p>
   * <p>
   * The value of this constant is 247
   * </p>
   */
  LM_VC_C(247),
  /**
   * <p>
   *  proc_sync feature (for multi-arm)
   * </p>
   * <p>
   * The value of this constant is 248
   * </p>
   */
  LM_PROCSYNC_C(248),
  /**
   * <p>
   *  SING AVOID ON
   * </p>
   * <p>
   * The value of this constant is 248
   * </p>
   */
  LM_SD_ON_C(248),
  /**
   * <p>
   *  SING AVOID OFF
   * </p>
   * <p>
   * The value of this constant is 249
   * </p>
   */
  LM_SD_OFF_C(249),
  /**
   * <p>
   *  FOR FUTURE EXPANSION FOR MOTION OPTIONS
   * </p>
   * <p>
   * The value of this constant is 249
   * </p>
   */
  LM_MOTN_EXT_C(249),
  /**
   * <p>
   *  MROT (Min Rotation)
   * </p>
   * <p>
   * The value of this constant is 250
   * </p>
   */
  LM_MROT_C(250),
  /**
   * <p>
   *  Path Switching
   * </p>
   * <p>
   * The value of this constant is 251
   * </p>
   */
  LM_PS_C(251),
  /**
   * <p>
   *  AccuCal II instruction
   * </p>
   * <p>
   * The value of this constant is 252
   * </p>
   */
  LM_CALB_C(252),
  /**
   * <p>
   *  Independent/Simultaneous GP
   * </p>
   * <p>
   * The value of this constant is 253
   * </p>
   */
  LM_MLGP_C(253),
  /**
   * <p>
   *  motion
   * </p>
   * <p>
   * The value of this constant is 254
   * </p>
   */
  LM_MOVE_C(254),
  /**
   * <p>
   *  terminator
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  LM_TERM_C(255),
  /**
   * <p>
   *  integer (1 byte)
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_CNST_BYT_C(1),
  /**
   * <p>
   *  longword integer (4 bytes)
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_CNST_LNG_C(2),
  /**
   * <p>
   *  Short real (4 bytes)
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_CNST_FLT_C(3),
  /**
   * <p>
   *  OFF
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SPC_OFF_C(0),
  /**
   * <p>
   *  ON
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SPC_ON_C(1),
  /**
   * <p>
   *  ENABLE
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_SPC_ENB_C(2),
  /**
   * <p>
   *  DISABLE
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_SPC_DIS_C(3),
  /**
   * <p>
   *  START
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_SPC_START_C(4),
  /**
   * <p>
   *  STOP
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_SPC_STOP_C(5),
  /**
   * <p>
   *  RESET
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_SPC_RESET_C(6),
  /**
   * <p>
   *  LPOS
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_SPC_LPOS_C(7),
  /**
   * <p>
   *  JPOS
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  LM_SPC_JPOS_C(8),
  /**
   * <p>
   *  OPEN
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  LM_SPC_OPEN_C(9),
  /**
   * <p>
   *  CLOSE
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  LM_SPC_CLOSE_C(10),
  /**
   * <p>
   *  ENTER
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  LM_SPC_ENTER_C(11),
  /**
   * <p>
   *  EXIT
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  LM_SPC_EXIT_C(12),
  /**
   * <p>
   *  OPEN FOR CLAMP
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  LM_SPC_COPEN_C(13),
  /**
   * <p>
   *  REPOZITION FOR CLAMP
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  LM_SPC_CREP_C(14),
  /**
   * <p>
   *  HIGH FOR SPOT[..,V=H,...]
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  LM_SPC_SVHIGH_C(17),
  /**
   * <p>
   *  MID FOR SPOT[..,V=M,...]
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  LM_SPC_SVMID_C(18),
  /**
   * <p>
   *  LOW FOR SPOT[..,V=L,...]
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  LM_SPC_SVLOW_C(19),
  /**
   * <p>
   *  * of BACKUP[i]=* etc.
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  LM_SPC_NOCHG_C(20),
  /**
   * <p>
   *  OFF EDGE
   * </p>
   * <p>
   * The value of this constant is 21
   * </p>
   */
  LM_SPC_OFFE_C(21),
  /**
   * <p>
   *  ON EDGE
   * </p>
   * <p>
   * The value of this constant is 22
   * </p>
   */
  LM_SPC_ONE_C(22),
  /**
   * <p>
   *  TMP_DISABLE
   * </p>
   * <p>
   * The value of this constant is 23
   * </p>
   */
  LM_SPC_TMPDIS_C(23),
  /**
   * <p>
   *  RELEASE
   * </p>
   * <p>
   * The value of this constant is 24
   * </p>
   */
  LM_SPC_DIS_RELEASE_C(24),
  /**
   * <p>
   *  "*" of SPOT[P=*, S=, BU=*]
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  LM_SPC_NOCHG2_C(255),
  /**
   * <p>
   *  reserve (untaught)
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_MTN_UNT_C(0),
  /**
   * <p>
   *  joint
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_MTN_JNT_C(1),
  /**
   * <p>
   *  linear
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_MTN_LIN_C(2),
  /**
   * <p>
   *  circular
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_MTN_CIR_C(3),
  /**
   * <p>
   *  inclination control
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_MTN_INCL_C(4),
  /**
   * <p>
   *  home
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_MTN_HOME_C(5),
  /**
   * <p>
   *  circle arc
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_MTN_ARC_C(6),
  /**
   * <p>
   *  normal position
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_MTN_NPOS_C(0),
  /**
   * <p>
   *  position register
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_MTN_RPOS_C(1),
  /**
   * <p>
   *  pallet motion
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_MTN_PALLET_C(2),
  /**
   * <p>
   *  Simple palletizing
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_MTN_PAL2_C(3),
  /**
   * <p>
   *  normal speed
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_MTN_NSPD_C(0),
  /**
   * <p>
   *  Direct register speed
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  LM_MTN_RSPD_C(64),
  /**
   * <p>
   *  Indirect register speed
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  LM_MTN_RRSPD_C(128),
  /**
   * <p>
   *  Application speed
   * </p>
   * <p>
   * The value of this constant is 192
   * </p>
   */
  LM_MTN_APPLSPD_C(192),
  /**
   * <p>
   *  Mask for speed type field
   * </p>
   * <p>
   * The value of this constant is 192
   * </p>
   */
  LM_MTN_UNSPD_C(192),
  /**
   * <p>
   *  % (joint)
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_MTN_PER_C(0),
  /**
   * <p>
   *  mm/sec
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_MTN_MMSEC_C(1),
  /**
   * <p>
   *  cm/min
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_MTN_CMMIN_C(2),
  /**
   * <p>
   *  inch/min
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_MTN_INCMN_C(3),
  /**
   * <p>
   *  deg/sec
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_MTN_DGSEC_C(4),
  /**
   * <p>
   *  sec
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_MTN_SEC_C(5),
  /**
   * <p>
   *  msec
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_MTN_MSEC_C(6),
  /**
   * <p>
   *  max_speed
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_MTN_MSPD_C(7),
  /**
   * <p>
   *  Fine
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_MTN_FINE_C(0),
  /**
   * <p>
   *  Cnt
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  LM_MTN_CNT_C(128),
  /**
   * <p>
   *  CD
   * </p>
   * <p>
   * The value of this constant is 192
   * </p>
   */
  LM_MTN_CD_C(192),
  /**
   * <p>
   *  Cnt (Indeirect)
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  LM_MTN_RCNT_C(64),
  /**
   * <p>
   *  System defined AIR
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SYS_AIR(0),
  /**
   * <p>
   *  User defined AIR
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_USER_AIR(1),
  /**
   * <p>
   *  Not use AIR
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_NONE_AIR(2),
  /**
   * <p>
   *  B=
   * </p>
   * <p>
   * The value of this constant is 71
   * </p>
   */
  LM_SP1_BB(71),
  /**
   * <p>
   *  V=
   * </p>
   * <p>
   * The value of this constant is 72
   * </p>
   */
  LM_SP1_V(72),
  /**
   * <p>
   *  S=
   * </p>
   * <p>
   * The value of this constant is 73
   * </p>
   */
  LM_SP1_S(73),
  /**
   * <p>
   *  B=
   * </p>
   * <p>
   * The value of this constant is 74
   * </p>
   */
  LM_SP1_BA(74),
  /**
   * <p>
   *  B=
   * </p>
   * <p>
   * The value of this constant is 75
   * </p>
   */
  LM_SP2_BB(75),
  /**
   * <p>
   *  V=
   * </p>
   * <p>
   * The value of this constant is 76
   * </p>
   */
  LM_SP2_V(76),
  /**
   * <p>
   *  S=
   * </p>
   * <p>
   * The value of this constant is 77
   * </p>
   */
  LM_SP2_S(77),
  /**
   * <p>
   *  B=
   * </p>
   * <p>
   * The value of this constant is 78
   * </p>
   */
  LM_SP2_BA(78),
  /**
   * <p>
   *  EQ=
   * </p>
   * <p>
   * The value of this constant is 79
   * </p>
   */
  LM_SP2_EQ(79),
  /**
   * <p>
   *  WC=
   * </p>
   * <p>
   * The value of this constant is 80
   * </p>
   */
  LM_SP2_WC(80),
  /**
   * <p>
   *  
   * </p>
   * <p>
   * The value of this constant is 81
   * </p>
   */
  LM_SP2_CT(81),
  /**
   * <p>
   *  
   * </p>
   * <p>
   * The value of this constant is 82
   * </p>
   */
  LM_SP2_SN(82),
  /**
   * <p>
   *  
   * </p>
   * <p>
   * The value of this constant is 83
   * </p>
   */
  LM_SP2_VL(83),
  /**
   * <p>
   *  AOV(,)
   * </p>
   * <p>
   * The value of this constant is 84
   * </p>
   */
  LM_SP2_AV(84),
  /**
   * <p>
   *  
   * </p>
   * <p>
   * The value of this constant is 85
   * </p>
   */
  LM_STK_FCTR(85),
  /**
   * <p>
   *  Tip Stick data (invisible)
   * </p>
   * <p>
   * The value of this constant is 86
   * </p>
   */
  LM_TIP_STIK(86),
  /**
   * <p>
   *  
   * </p>
   * <p>
   * The value of this constant is 87
   * </p>
   */
  LM_AVEN_C(87),
  /**
   * <p>
   *  Equalization pressure EP=
   * </p>
   * <p>
   * The value of this constant is 88
   * </p>
   */
  LM_SP2_EP(88),
  /**
   * <p>
   *  Servogun Zero Master[]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SG_ZERO_C(0),
  /**
   * <p>
   *  SV_SPOT[P=1, S=1]
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SVG_SINGLE(1),
  /**
   * <p>
   *  SV_SPOT[P=(1,1), S=(1,1)]
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_SVG_DOUBLE(2),
  /**
   * <p>
   *  Pressure level Gun1
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_PRES_GUN1_C(3),
  /**
   * <p>
   *  Pressure level Gun2
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_PRES_GUN2_C(4),
  /**
   * <p>
   *  Press Motion[AP=,P=]
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_SGPM1_DST_C(5),
  /**
   * <p>
   *  Press Motion[AP=(,),P=(,)]
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_SGPM2_DST_C(6),
  /**
   * <p>
   *  GUN ATTACH [...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SVGC_ATCH_C(0),
  /**
   * <p>
   *  GUN DETACH [...]
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SVGC_DTCH_C(1),
  /**
   * <p>
   *  GUN EXCHANGE [...]
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_SVGC_XCHG_C(2),
  /**
   * <p>
   *  TOOL ATTACH [...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SVTC_ATCH_C(0),
  /**
   * <p>
   *  TOOL DETACH [...]
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SVTC_DTCH_C(1),
  /**
   * <p>
   *  IBS ATTACH GROUP_NAME
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_IBSC_ATCH_C(0),
  /**
   * <p>
   *  IBS DETACH GROUP_NAME
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_IBSC_DTCH_C(1),
  /**
   * <p>
   *  IBS attach SEGMENT_NUM
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_IBPX_ATCH_C(0),
  /**
   * <p>
   *  IBS detach SEGMENT_NUM
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_IBPX_DTCH_C(1),
  /**
   * <p>
   *  Soft float [...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SFLTSCD_C(0),
  /**
   * <p>
   *  Sfot float END
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_SFLTEND_C(3),
  /**
   * <p>
   *  Follow up
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_SFLTFLWUP_C(2),
  /**
   * <p>
   *  Array of force command
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_CC_ARRY_C(0),
  /**
   * <p>
   *  Force Basic
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_CC_BD_C(1),
  /**
   * <p>
   *  Force Basic Array
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_CC_BDA_C(2),
  /**
   * <p>
   *  Insertion
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_CC_ID_C(3),
  /**
   * <p>
   *  Insertion Array
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_CC_IDA_C(4),
  /**
   * <p>
   *  GET Force postion
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_CC_GET_C(5),
  /**
   * <p>
   *  DEL Force postion
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_CC_DEL_C(6),
  /**
   * <p>
   *  Calibration tool
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_CC_CAL_C(7),
  /**
   * <p>
   *  Sensor Diagnosis
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  LM_CC_SNS_C(8),
  /**
   * <p>
   *  Get monitor
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  LM_CC_GM_C(9),
  /**
   * <p>
   *  Enable monitor
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  LM_CC_EM_C(10),
  /**
   * <p>
   *  Disable monitor
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  LM_CC_DM_C(11),
  /**
   * <p>
   *  SNAP vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_SNAP_C(0),
  /**
   * <p>
   *  FIND vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_FIND_C(1),
  /**
   * <p>
   *  FIND_NEXT vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_FNDNXT_C(2),
  /**
   * <p>
   *  MEASURE vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_MEASURE_C(3),
  /**
   * <p>
   *  CALC_OFFSET vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_CALOFST_C(4),
  /**
   * <p>
   *  DISPLAY vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_DISPLAY_C(5),
  /**
   * <p>
   *  CLEAR_MONITOR vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_CLRMON_C(6),
  /**
   * <p>
   *  DO_PROCESS vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_DOPROC_C(7),
  /**
   * <p>
   *  SAVE_QIMG vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  LM_SAVQIMG_C(8),
  /**
   * <p>
   *  DISP_QIMG vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  LM_DSPQIMG_C(9),
  /**
   * <p>
   *  CAM_CALIB vision mnemonic
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  LM_CAMCALI_C(10),
  /**
   * <p>
   *  VIS SNAP ... VIEW[...]
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  LM_SNAP2_C(11),
  /**
   * <p>
   *  VIS FIND ... VIEW[...]
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  LM_FIND2_C(12),
  /**
   * <p>
   *  VIS FIND_NEXT ... VIEW[...]
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  LM_FNDNXT2_C(13),
  /**
   * <p>
   *  VIS CALC_OFFSET ...
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  LM_CALOFST2_C(14),
  /**
   * <p>
   *  VIS DISPLAY ... VIEW[...]
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  LM_DISPVIEW_C(15),
  /**
   * <p>
   *  VIS DO_PROCESS ...
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  LM_DOPROC2_C(16),
  /**
   * <p>
   *  VIS DO_PROC_NEXT ...
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  LM_DOPRONXT_C(17),
  /**
   * <p>
   *  VIS DISPLAY ...
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  LM_DISPLAY2_C(18),
  /**
   * <p>
   *  VIS POP_QUEUE
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  LM_POPQU_C(19),
  /**
   * <p>
   *  VIS WAIT_PART ...
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  LM_WAIPT_C(20),
  /**
   * <p>
   *  STICK DETECT ON
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_STCK_ON_C(0),
  /**
   * <p>
   *  STICK DETECT OFF
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_STCK_OF_C(1),
  /**
   * <p>
   *  PAYLOAD[...]
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SGPLST_C(1),
  /**
   * <p>
   *  PAYLOAD[GP:...]
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_MLPLST_C(2),
  /**
   * <p>
   *  LOW SPEED DATA
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_LOWD_C(3),
  /**
   * <p>
   *  LOW SPEED PROC
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  LM_LOWP_C(4),
  /**
   * <p>
   *  HIGH SPEED DATA
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  LM_HIGHD_C(5),
  /**
   * <p>
   *  ESTIMATION
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  LM_EST_C(6),
  /**
   * <p>
   *  ESTIMATION M=...
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  LM_EST_M_C(7),
  /**
   * <p>
   *  APPLY EST [...]
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  LM_APLY_C(8),
  /**
   * <p>
   *  LOAD CLUTCH START
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_LDCL_STA_C(0),
  /**
   * <p>
   *  LOAD CLUTCH END
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_LDCL_END_C(1),
  /**
   * <p>
   *  Joint Max Speed
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_MSPD_JNT_C(1),
  /**
   * <p>
   *  Linear Max Speed
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_MSPD_LIN_C(2),
  /**
   * <p>
   *  2DVIS DO_PROCESS menmonic
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_PCVIS_DOPRO_C(1),
  /**
   * <p>
   *  STITCH[...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_STCH_START_C(0),
  /**
   * <p>
   *  STITCH END
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_STCH_END_C(1),
  /**
   * <p>
   *  Approach_STOP[...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_IA_IASTOP_C(0),
  /**
   * <p>
   *  Approach_WAIT[...]
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_IA_IAWAIT_C(1),
  /**
   * <p>
   *  Approach_RATE[...]
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_IA_IARATE_C(2),
  /**
   * <p>
   *  VIS DO_PROCESS menmonic
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_SIVIS_DOPRO_C(1),
  /**
   * <p>
   *  LM_IWC_ISOCON_C
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_IWC_ISOCON_C(0),
  /**
   * <p>
   *  LM_IWC_RSTSTP_C
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_IWC_RSTSTP_C(1),
  /**
   * <p>
   *  LM_IWC_RSTWLD_C
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_IWC_RSTWLD_C(2),
  /**
   * <p>
   *  PROC_START[...]
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  LM_PSYN_START_C(0),
  /**
   * <p>
   *  PROC_END
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  LM_PSYN_END_C(1),
  /**
   * <p>
   *  PROC_SYNC[...]
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  LM_PSYN_SYNC_C(2),
  /**
   * <p>
   *  INPOS[..]
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  LM_PSYN_INPOS_C(3),
  ;

  private final int value;
  FRELMCodeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
