package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",  //조인테이블 명
        joinColumns = @JoinColumn(name = "category_id"), //현재 엔티티를 참조하는 fk
            inverseJoinColumns = @JoinColumn(name = "item_id")) // 반대방향 엔티티를 참조하는 fk
   private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private  Category parent;

    @OneToMany(mappedBy = "parent")
    private  List<Category> child =new ArrayList<>();

    //연관관계 메서드
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

    //@JoinColumn(name = "parent_id")


}
