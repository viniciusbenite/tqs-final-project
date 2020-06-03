import React from 'react';
import Cabeleireiro from './Cabeleireiro';

export default function CabeleireirosList({cabeleireiros}) {
    if(cabeleireiros.length === 0){
        return (
            <div className="empty-search">
                <h3>Não existem cabeleireiros disponíveis</h3>

            </div>
        );
    }
    
    return (
        <section className="cabeleireiroslist">
        <div className="cabeleireiroslist-center">
            {cabeleireiros.map(item => {
                return <Cabeleireiro key={item.id} cabeleireiro={item} />;
                })}
        </div>
        </section>
    );
}
