/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import java.util.Comparator;
	import org.apache.commons.lang.StringUtils;
	import com.github.obullxl.jeesite.web.enums.TrueFalseEnum;

/**
 * A data object class directly models database table <tt>atom_config</tt>.
 */
public class ConfigDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;

	/**
	 * 是否有效
	 */
	public boolean isActive() {
        TrueFalseEnum enm = TrueFalseEnum.findByCode(this.state);
        if (enm != TrueFalseEnum.TRUE) {
            return false;
        }

        return true;
    }
    
    /**
     * 排序比较器
     */
    public static final Comparator<ConfigDTO> COMPARATOR       = new Comparator<ConfigDTO>() {
        public int compare(ConfigDTO src, ConfigDTO dst) {
            if (src == null) {
                return -1;
            }

            if (dst == null) {
                return 1;
            }

            String scatg = StringUtils.trimToEmpty(src.getCatg()) + StringUtils.trimToEmpty(src.getName());
            String dcatg = StringUtils.trimToEmpty(dst.getCatg()) + StringUtils.trimToEmpty(dst.getName());

            return scatg.compareTo(dcatg);
        }
    };

	/** column:id */
	private long id;

	/** column:catg */
	private String catg;

	/** column:name */
	private String name;

	/** column:state */
	private String state;

	/** column:value */
	private String value;

	/** column:cvalue */
	private String cvalue;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getCatg() {
		return catg;
	}

	public void setCatg(String catg) {
		this.catg = catg;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

}
