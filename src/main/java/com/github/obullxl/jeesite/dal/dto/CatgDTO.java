/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import java.util.List;
	import java.util.ArrayList;
	import java.util.Comparator;


/**
 * A data object class directly models database table <tt>atom_catg</tt>.
 */
public class CatgDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;

	/** parent */
    private CatgDTO           parent;

    /** children */
    private List<CatgDTO>     children         = new ArrayList<CatgDTO>();

    public CatgDTO getParent() {
        return parent;
    }

    public void setParent(CatgDTO parent) {
        this.parent = parent;
    }

    public List<CatgDTO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<CatgDTO>();
        }

        return children;
    }

    public void setChildren(List<CatgDTO> children) {
        this.children = children;
    }
    
    /**
     * ÅÅÐò±È½ÏÆ÷
     */
    public static final Comparator<CatgDTO> COMPARATOR = new Comparator<CatgDTO>() {
        public int compare(CatgDTO src, CatgDTO dst) {
            if (src == null) {
                return -1;
            }

            if (dst == null) {
                return 1;
            }

            return (int) (src.getSort() - dst.getSort());
        }
    };


	/** column:id */
	private long id;

	/** column:code */
	private String code;

	/** column:top */
	private String top;

	/** column:catg */
	private long catg;

	/** column:sort */
	private long sort;

	/** column:name */
	private String name;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
	public long getCatg() {
		return catg;
	}

	public void setCatg(long catg) {
		this.catg = catg;
	}
	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
