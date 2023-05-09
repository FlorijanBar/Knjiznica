package com.example.knjiznica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Knjizicar;

@Service
@Transactional
public class KnjizicarServiceImpl implements KnjizicarService{

    @Override
    public Knjizicar createKnjizicar(Knjizicar Knjizicar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createKnjizicar'");
    }

    @Override
    public Iterable<Knjizicar> getAllKnjizicar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllKnjizicar'");
    }

    @Override
    public Iterable<Knjizicar> getKnjizicarStudij(String studij) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKnjizicarStudij'");
    }

    @Override
    public Knjizicar getKnjizicar(long id_Knjizicar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKnjizicar'");
    }
    
}
