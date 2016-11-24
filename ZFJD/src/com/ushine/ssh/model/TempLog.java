package com.ushine.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 临时日志实体
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_TEMP_LOG")
public class TempLog {
		private String id;//id
		private String systemName;//系统名称
		private String dataType;//数据类型	
		private String operatePerson;//操作人员
		private String askTime;//请求时间
		private String operateFruit;//操作结果
		
		
		
		
		/******************get方法********************/
		/**
		 * ID
		 * @return
		 */
		@Id
		@GenericGenerator(name = "uId", strategy = "uuid.hex")
		@GeneratedValue(generator = "uId")
		@Column(name = "ID", length = 32)
		public String getId() {
			return id;
		}
		/**
		 * 系统名称
		 * @return
		 */
		@Column(name = "SYSTEM_NAME", length = 60)
		public String getSystemName() {
			return systemName;
		}
		/**
		 * 数据类型
		 * @return
		 */
		@Column(name = "DATA_TYPE", length = 60)
		public String getDataType() {
			return dataType;
		}
		/**
		 * 操作人员
		 * @return
		 */
		@Column(name = "OPERATE_PERSON", length = 60)
		public String getOperatePerson() {
			return operatePerson;
		}
		/**
		 * 请求时间
		 * @return
		 */
		@Column(name="ASK_TIME",columnDefinition="TIMESTAMP")
		public String getAskTime() {
			return askTime;
		}
		/**
		 * 操作结果
		 * @return
		 */
		@Column(name = "OPERATE_FRUIT", length = 60)
		public String getOperateFruit() {
			return operateFruit;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*******************set方法*************************/
		/**
		 * id
		 * @param id
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * 系统时间
		 * @param systemName
		 */
		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}
		/**
		 * 数据类型
		 * @param dataType
		 */
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		/**
		 * 操作人员
		 * @param operatePerson
		 */
		public void setOperatePerson(String operatePerson) {
			this.operatePerson = operatePerson;
		}
		/**
		 * 请求时间
		 * @param askTime
		 */
		public void setAskTime(String askTime) {
			this.askTime = askTime;
		}
		/**
		 * 操作结果
		 * @param operateFruit
		 */
		public void setOperateFruit(String operateFruit) {
			this.operateFruit = operateFruit;
		}
		
}
