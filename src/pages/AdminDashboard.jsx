import React, { useEffect, useState } from 'react'
import NavBar from '../component/NavBar'
import AdminCutomers from '../sections/AdminCutomers';
import AdminTrainers from '../sections/AdminTrainers';
import AdminEquipment from '../sections/AdminEquipment';
import AdminMember from '../sections/AdminMember';
import AdminPurchase from '../sections/AdminPurchase';

export default function AdminDashboard() {
    const [option, setOption] = useState('customers');
    const [submenuDisplay, setSubmenuDisplay] = useState(true);


    return (
        <>
            <NavBar user='admin' />
            <div className="body-container">
                <div className="a-container">
                    <div className="siderbar-menu">
                        <div className='menu-toggle' id='menu-toggle' onClick={() => {
                            console.log(submenuDisplay);
                            setSubmenuDisplay(!submenuDisplay)

                        }}>Menu <img src="/img/line-angle-down-icon.svg" alt="" /></div>
                       <ul className={`w ${submenuDisplay?'show':''}`} >
                            <li>
                                <div className={`menu-option ${option === 'customers' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('customers')}}>
                                    Customers
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'trainers' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('trainers')}}>
                                    Trainers
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'equipment' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('equipment')}}>
                                    Equipment
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'membership' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('membership')}}>
                                    Memberships Plans
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'Purchase' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('Purchase')}}>
                                    Store Purchase
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div className="main-container" id="mainContainer">
                        {option === 'customers' && <AdminCutomers />}
                        {option === 'trainers' && <AdminTrainers />}
                        {option === 'equipment' && <AdminEquipment />}
                        {option === 'membership' && <AdminMember />}
                        {option === 'Purchase' && <AdminPurchase />}
                    </div>
                </div>
            </div>
            <footer>
                <div className="divider-y"></div>
                <p>&copy; 2023 Fitsta.com All rights reserved.</p>

            </footer>
        </>
    )
}
