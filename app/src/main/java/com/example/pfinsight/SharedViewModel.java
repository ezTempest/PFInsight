package com.example.pfinsight;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MyDocument selectedObject;


    public void setSelectedObject(MyDocument object) {
        if(object == null){
            System.out.println("Nulo no sharedview");
        }else{
            System.out.println("Não nulo no sharedview");
            System.out.println("xerecaView "+object.getNome());
        }
        System.out.println("Isso foi setado");
        this.selectedObject = object;
        System.out.println("Isso foi setado"+this.selectedObject.getNome());
    }

    public MyDocument getSelectedObject() {
        System.out.println("teste dentro do get "+selectedObject.getNome());
        return selectedObject;
    }
}