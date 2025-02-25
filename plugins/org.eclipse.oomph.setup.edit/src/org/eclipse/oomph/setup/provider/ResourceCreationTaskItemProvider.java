/*
 * Copyright (c) 2014 Eike Stepper (Loehne, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.setup.provider;

import org.eclipse.oomph.setup.ResourceCreationTask;
import org.eclipse.oomph.setup.SetupPackage;
import org.eclipse.oomph.setup.util.SetupUtil;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 * This is the item provider adapter for a {@link org.eclipse.oomph.setup.ResourceCreationTask} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceCreationTaskItemProvider extends SetupTaskItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceCreationTaskItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addForcePropertyDescriptor(object);
      addContentPropertyDescriptor(object);
      addTargetURLPropertyDescriptor(object);
      addEncodingPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Force feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addForcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_ResourceCreationTask_force_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_ResourceCreationTask_force_feature", "_UI_ResourceCreationTask_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        SetupPackage.Literals.RESOURCE_CREATION_TASK__FORCE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Content feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addContentPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_ResourceCreationTask_content_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_ResourceCreationTask_content_feature", "_UI_ResourceCreationTask_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        SetupPackage.Literals.RESOURCE_CREATION_TASK__CONTENT, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Target URL feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTargetURLPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_ResourceCreationTask_targetURL_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_ResourceCreationTask_targetURL_feature", "_UI_ResourceCreationTask_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        SetupPackage.Literals.RESOURCE_CREATION_TASK__TARGET_URL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Encoding feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEncodingPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_ResourceCreationTask_encoding_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_ResourceCreationTask_encoding_feature", "_UI_ResourceCreationTask_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        SetupPackage.Literals.RESOURCE_CREATION_TASK__ENCODING, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This returns ResourceCreationTask.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ResourceCreationTask")); //$NON-NLS-1$
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean shouldComposeCreationImage()
  {
    return true;
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    String label = ((ResourceCreationTask)object).getTargetURL();
    return label == null || label.length() == 0 ? getString("_UI_ResourceCreationTask_type") : //$NON-NLS-1$
        getString("_UI_ResourceCreationTask_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(ResourceCreationTask.class))
    {
      case SetupPackage.RESOURCE_CREATION_TASK__FORCE:
      case SetupPackage.RESOURCE_CREATION_TASK__CONTENT:
      case SetupPackage.RESOURCE_CREATION_TASK__TARGET_URL:
      case SetupPackage.RESOURCE_CREATION_TASK__ENCODING:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  @Override
  public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    if (commandClass == DragAndDropCommand.class && object instanceof ResourceCreationTask && domain != null)
    {
      Collection<?> collection = commandParameter.getCollection();
      if (collection != null)
      {
        for (Object value : collection)
        {
          if (value instanceof URI)
          {
            URI uri = (URI)value;
            if (!"setup".equals(uri.fileExtension())) //$NON-NLS-1$
            {
              try
              {
                String content = new String(domain.getResourceSet().getURIConverter().createInputStream(uri).readAllBytes(), StandardCharsets.UTF_8);

                DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
                return new BaseDragAndDropCommand(domain, object, detail.location, detail.operations, detail.operation, collection)
                {
                  @Override
                  protected boolean prepareDropMoveOn()
                  {
                    dragCommand = IdentityCommand.INSTANCE;
                    dropCommand = createSetCommand(domain, (EObject)owner, SetupPackage.Literals.RESOURCE_CREATION_TASK__CONTENT, SetupUtil.escape(content));
                    return dropCommand.canExecute();
                  }
                };
              }
              catch (Exception ex)
              {
                //$FALL-THROUGH$
              }
            }
          }
        }
      }
    }

    return super.createCommand(object, domain, commandClass, commandParameter);
  }
}
